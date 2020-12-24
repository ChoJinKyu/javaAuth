sap.ui.define([
        "sap/ui/core/mvc/Controller",
        "sap/m/MessageToast",
        "sap/m/MessageBox"
	],
	/**
     * @param {typeof sap.ui.core.mvc.Controller} Controller
     */
	function (Controller, MessageToast, MessageBox) {
		"use strict";

		return Controller.extend("cm.bookMgr.controller.BookMgrView", {
			onInit: function () {

            },

            onCreate : function () {
                var oList = this.byId("BookList");
                var oBinding = oList.getBinding("items");
                var oContext = oBinding.create({
                        "ID" : "",
                        "title" : "T",
                        "stock" : ""
                    });

                // Select and focus the table row that contains the newly created entry
                oList.getItems().some(function (oItem) {
                    if (oItem.getBindingContext() === oContext) {
                        oItem.focus();
                        oItem.setSelected(true);
                        return true;
                    }
                });
            },

            onDelete : function () {

            },

            onSave : function () {
                
                var oView = this.getView();
                var fnSuccess = function () {
                    oView.setBusy(false);
                    MessageToast.show("저장 되었습니다.");
                    this.onRefresh();
                }.bind(this);

                var fnError = function (oError) {
                    oView.setBusy(false);
                    MessageBox.error(oError.message);
                }.bind(this);

                oView.setBusy(true);
                this.getView().getModel().submitBatch("bookGroup").then(fnSuccess, fnError);

            }
		});
	});
