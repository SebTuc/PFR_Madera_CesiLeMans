<!-- JS TREE -->
<script type='text/javascript' src='<%=request.getContextPath()%>/resources/js/jstree.min.js'></script>

<!-- Methodes custom -->
<script type="text/javascript">
  function loadCategorizedTrees(idItemsList, dataItemList, idCategoryTree, dataCategoryTree, idpushButton) {

    var itemList = $('#' + idItemsList);
    var categoryTree = $('#' + idCategoryTree);
    var pushButton = $('#' + idpushButton);

    var withdrawBtnStruct = '<button class="material-icons material-icons-button material-icon-delete delete-project" onclick="withdrawItem(this,' + idItemsList + ',' + idCategoryTree + ')"></button>'; // Structure du button de withdraw

    /* Initialisation des trees */
    if (dataItemList.length == 0) {
      $(itemList).html("<p class=\"h4 font-weight-light text-muted\">Aucun item disponible</p>")
    }

    $(itemList).jstree({
      "core": {
        "animation": 0, // Definit le temps d'animation en ms
        'check_callback': true, // Prend en compte les modification crud apporte au tree
        "themes": {
          "stripes": true
        }, // Change la couleur une ligne sur deux
        "data": dataItemList // Donnees à prendre en compte pour le generation du tree
      },
      "types": {
        "#": {
          "max_children": -1, // Nombre d'element de même branche max (-1 infini)
          "max_depth": 1, // Nombre de sous branche max
          "valid_children": ["item"]
        },
        "item": {
          "icon": "material-icons material-icon-build",
        }

      },
      "plugins": [ // Plugins utilises par le tree
        "state", "types", "wholerow"
      ]
    });

    $(categoryTree).jstree({
      "core": {
        "animation": 0,
        'check_callback': function (operation, node, node_parent, node_position, more) {

          // Operation : 'create_node', 'rename_node', 'delete_node', 'move_node', 'copy_node', 'edit'
          console.log(operation);
          switch (operation) {
            case "create_node":

              // REQUETE AJAX ADD                 
              return true;

            case "delete_node":

              // REQUETE AJAX DELETE
              return true;

            default:
              break;
          }
        },
        "themes": {
          "stripes": true
        },
        "data": dataCategoryTree.map(function (item) {
          if (item.children != null) {
            item.children = item.children.map(function (children) {

              // Ajout des buttons
              children.text += withdrawBtnStruct;
              return children;
            });
          }

          return item
        })
      },
      "types": {
        "#": {
          "max_children": -1, // Nombre d'element de même branche max (-1 infini)
          "max_depth": 2,
          "valid_children": ["category"]
        },
        "category": {
          "valid_children": ["item"],
          "icon": "material-icons material-icon-collections"
        },
        "item": {
          "icon": "material-icons material-icon-build",
        }
      },
      "plugins": [
        "state", "types", "wholerow"
      ]
    });

    if (dataCategoryTree.length == 0) {
      $(categoryTree).html("<p class=\"h4 font-weight-light text-muted\">Aucune cat&eacute;gorie disponible</p>")
    }



    /* Methodes de gestion d'ajout */

    var pushItem = function () {
      var itemListTr = $(itemList).jstree(true);
      var categoryTreeTr = $(categoryTree).jstree(true);

      // Items selectionnes dans chaque tree
      var itemListSel = itemListTr.get_selected();
      var categoryTreeSel = categoryTreeTr.get_selected();

      if (itemListSel.length > 0 && categoryTreeSel.length > 0) {

        categoryTreeSel = categoryTreeSel[0];
        var categoryTreeSelNode = $(categoryTree).jstree("get_node", categoryTreeSel);

        if (categoryTreeSelNode.type === "category") {

          // Recup des donnees de la node
          itemListSel = itemListSel[0];
          var itemListSelNode = $(itemList).jstree("get_node", itemListSel);

          // Ajout en tant qu'enfant d'une category
          $(categoryTree).jstree("create_node", categoryTreeSel, {
            "text": itemListSelNode.text + withdrawBtnStruct,
            "metadata": {
              id: itemListSelNode.id
            },
            "type": "item"
          }, 'last');
          $(itemList).jstree("delete_node", itemListSel);
        }

      }
    }


    /* Init des listener */

    $(pushButton).click(pushItem);

    // Gestion activation / desactivation du button push
    $(categoryTree).add(itemList).bind(
      "select_node.jstree",
      function () {
        $(pushButton).attr("disabled", true);

        // Items selectionnes dans chaque tree
        var itemListNodeSelected = $(itemList).jstree("get_node", $(itemList).jstree(true).get_selected()[0]);
        var categoryTreeNodeSelected = $(categoryTree).jstree("get_node", $(categoryTree).jstree(true).get_selected()[0]);


        if (itemListNodeSelected != null && categoryTreeNodeSelected != null) {
          if (categoryTreeNodeSelected.type == "category") {
            $(pushButton).removeAttr('disabled');
          }
        }
      }
    );
  }

  function withdrawItem(event, items, categ) {
    console.log(event)
    // Items selectionnes le tree
    var categoryTreeSelId = $(event.parentNode).parent('li').attr('id');

    if (categoryTreeSelId != null) {

      var categoryTreeSelNode = $(categ).jstree("get_node", categoryTreeSelId);

      if (categoryTreeSelNode != null) {

        // Ajout en tant qu'enfant d'une category
        $(items).jstree("create_node", '#', {
          "text": $('<div>' + categoryTreeSelNode.text + '</div>').text(),
          "metadata": {
            id: categoryTreeSelNode.id
          },
          "type": "item"
        }, 'last');
        $(categ).jstree("delete_node", categoryTreeSelNode);
      }
    }
  }


</script>