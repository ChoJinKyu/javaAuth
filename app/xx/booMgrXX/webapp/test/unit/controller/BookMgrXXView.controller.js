/*global QUnit*/

sap.ui.define([
	"xx/booMgrXX/controller/BookMgrXXView.controller"
], function (Controller) {
	"use strict";

	QUnit.module("BookMgrXXView Controller");

	QUnit.test("I should test the BookMgrXXView controller", function (assert) {
		var oAppController = new Controller();
		oAppController.onInit();
		assert.ok(oAppController);
	});

});
