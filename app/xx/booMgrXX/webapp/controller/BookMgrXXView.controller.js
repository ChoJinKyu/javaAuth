sap.ui.define([
		"sap/ui/core/mvc/Controller"
	],
	/**
     * @param {typeof sap.ui.core.mvc.Controller} Controller
     */
	function (Controller) {
		"use strict";

		return Controller.extend("xx.booMgrXX.controller.BookMgrXXView", {
			onInit: function () {

            },
            onTest: function () {
                debugger

                /*
                var g=this;

                $.ajax({url: "../../../../user"}).done(function (data, status, jqxhr) {

                                var user = "Welcome! " + data.id;

                                debugger

                                var oText = new sap.m.Text({
                                    text:user
                                });

                                g.getView().byId("userVBox").addItem(oText);

                });
                */
			}
		});
	});
