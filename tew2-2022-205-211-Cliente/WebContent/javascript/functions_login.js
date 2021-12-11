function Model() {
	this.LAgentes = null;
	this.load = function() {
		this.LAgentes = AgentesServiciesRs.getAgentes();
	}
	this.comprueba = function (user, pass) {
		for (var a in this.LAgentes) {
			var agente = this.LAgentes[a];
			if ((agente.login == user) && (agente.passwd == pass)){
				localStorage.setItem('agente', agente.id);
				return true
			}
		}
	}
};

function Controller(varmodel) {
	var that = this;
	this.model = varmodel;
	this.init = function() {
		this.model.load();
		$("#btnEnviar").click(function(event){
			var user = $("#username").val();
			var pass = $("#passwd").val();
		    var bool = that.model.comprueba(user,pass);
		    if (bool == true) {	
		    	window.location.href="indexAgente.html";
		    }
		    else alert("Login incorrecto");
		});
		$("#logout").click(function(event){
			localStorage.removeItem('agente');
			window.location.href="index.html";
		})
	}
};

$(function() {
	var model = new Model();
	var control = new Controller(model);
	control.init();
});