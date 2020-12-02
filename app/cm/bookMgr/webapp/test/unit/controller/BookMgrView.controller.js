/*global QUnit*/

sap.ui.define([
	"cm/bookMgr/controller/BookMgrView.controller"
], function (Controller) {
	"use strict";

	QUnit.module("BookMgrView Controller");

	QUnit.test("I should test the BookMgrView controller", function (assert) {
		var oAppController = new Controller();
		oAppController.onInit();
		assert.ok(oAppController);
	});

});
