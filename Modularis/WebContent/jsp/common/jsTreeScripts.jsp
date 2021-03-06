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

    $(itemList).jstree({
      "core": {
        "animation": 0, // Definit le temps d'animation en ms
        'check_callback': true, // Prend en compte les modification crud apporte au tree
        'multiple': false,
        "themes": {
       	  'name': 'proton',
          "responsive": true,
          "ellipsis": true
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
        'multiple': false,
        'check_callback': function (operation, node, node_parent, node_position, more) {


          // Operation : 'create_node', 'rename_node', 'delete_node', 'move_node', 'copy_node', 'edit'
          switch (operation) {

            // REQUETE AJAX ADD                 
            case "create_node":
              console.log(node);
              console.log(node_parent);
              if (node != undefined && node_parent != undefined) {

                var rowData = { 'idProjet': node.metadata.id, 'idCatalogue': node_parent.original.metadata.id }
                var request = formatAjaxRequest(rowData);
                request += "&action=Push";

                $.ajax({
                  url: "",
                  type: 'POST',
                  data: request, 					// Données à transmettre
                  success: function () { return true },
                  error: function () { return false }
                });
              }
              break;

            // REQUETE AJAX DELETE
            case "delete_node":
              if (node != undefined && node_parent != undefined) {

                var rowData = { 'idProjet': node.original.metadata.id, 'idCatalogue': node_parent.original.metadata.id }
                var request = formatAjaxRequest(rowData);
                request += "&action=Withdraw";

                $.ajax({
                  url: "",
                  type: 'POST',
                  data: request, 					// Données à transmettre
                  success: function () { return true },
                  error: function () { return false }
                });
              }
              break;

            default:
              break;
          }
        },
        "themes": {
          'name': 'proton',
          "responsive": true,
          "ellipsis": true
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
              id: itemListSelNode.original.metadata.id
            },
            "type": "item"
          }, 'last');
          $(pushButton).attr("disabled", true);
          $(categoryTree).jstree("open_node",categoryTreeSel)

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


        if (itemListNodeSelected && categoryTreeNodeSelected) {

          var alreadyIn = false;
          $(categoryTree).jstree("redraw_node", categoryTreeNodeSelected, true, false, true);
          var childrenNodes = $(categoryTree).jstree("get_children_dom", categoryTreeNodeSelected)


          for (let index = 0; index < childrenNodes.length; index++) {
            const element = childrenNodes[index];
            var node = $(categoryTree).jstree("get_node", element)

            if (node.original.metadata.id == itemListNodeSelected.original.metadata.id) {
              alreadyIn = true;
            }

          }

          if (categoryTreeNodeSelected.type == "category" && !alreadyIn) {
            $(pushButton).removeAttr('disabled');
          }
        }
      }
    );
  }

  function withdrawItem(event, items, categ) {

    // Items selectionnes le tree
    var categoryTreeSelId = $(event.parentNode).parent('li').attr('id');

    if (categoryTreeSelId != null) {

      var categoryTreeSelNode = $(categ).jstree("get_node", categoryTreeSelId);

      if (categoryTreeSelNode != null) {

        $(categ).jstree("delete_node", categoryTreeSelNode);
        $(categ).jstree("deselect_all");
      }
    }
  }
</script>