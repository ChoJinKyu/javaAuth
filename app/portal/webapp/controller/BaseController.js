sap.ui.define([
   "sap/ui/core/mvc/Controller",
   "sap/ui/core/routing/History",
   "sap/ui/core/UIComponent",
   "sap/ui/model/json/JSONModel",
   "sap/ui/core/format/DateFormat"
], function (Controller, History, UIComponent, JSONModel, DateFormat) {
   "use strict";

   return Controller.extend("cm.codeMgr.controller.BaseController", {
       
		getRouter : function () {
			return UIComponent.getRouterFor(this);
		},

		onNavBack: function () {
			var oHistory, sPreviousHash;

			oHistory = History.getInstance();
			sPreviousHash = oHistory.getPreviousHash();

			if (sPreviousHash !== undefined) {
				window.history.go(-1);
			} else {
				this.getRouter().navTo("home", {}, true /*no history*/);
			}
        }

   });

});