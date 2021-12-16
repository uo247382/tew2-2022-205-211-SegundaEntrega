/**
 * 
 */
$(function() {
	 //Creamos el modelo con los datos y la conexión al servicio web.
	 var model = new Model();
	 model.load();
}) 

//Clase que contiene el Modelo de la aplicación.
function Model() {
	//Lista de pisos.
	this.tbPisos = null;
	this.IdArray = [];
	
	//Carga los datos del servicio sobreescribiendo el dato this.tbpisos.
	this.load = function() {
		this.tbPisos = PisosServicesRs.getPisos();
		this.IdArray = [];
		
	}
	//Llamamos al servicio de alta de Piso
	this.add = function(piso) {
		// Llamamos al servicio de alta de piso
		PisosServicesRs.savePiso({
			$entity : piso,
			$contentType : "application/json"
		}); 
		// Recargamos la lista de Pisos.
		this.load();
	}
	//Actualización de un Piso existente:
	this.edit = function(piso) {
		// Llamamos al servicio de borrado de Piso
		PisosServicesRs.updatePiso({
			$entity : piso,
			$contentType : "application/json"
		});
		// Recargamos la lista de Pisos.
		this.load();
	} 
	//Eliminación un Piso existente
	this.remove = function(id_piso) {
		// Llamamos al servicio de borrado de Piso
		PisosServicesRs.deletePiso({
			id : id_piso
		});
		// Recargamos la lista de Pisos.
		//this.load();
	
	}
	
	this.find = function(id_piso) {
		function checkPiso(obj) {
			return obj.id == id_piso;
		}
		// Buscamos los datos del Piso cuyo id_Piso sea el mismo que el
		// seleccionado
		var piso = this.tbPisos.find(checkPiso);
		return piso;
	}
};
 
//Clase que contiene la gestión de la capa Vista
function View(){
	var agente = localStorage.getItem('agente');
	//Lista en caso de que no haya un agente logueado: lista publica
	this.list = function (lista) {
		if(agente == null){
			$("#tbPiso").html( "<thead>" + "<tr>" + "<th></th>"
	                + "<th class = sorttable_nosort>id</th>" + "<th class = sorttable_nosort>idAgente</th>" + "<th >Precio</th>" + "<th class = sorttable_nosort>Direccion</th>"
	                + "<th class = sorttable_nosort>Ciudad</th>" + "<th class = sorttable_nosort>Anyo</th>" + "<th class = sorttable_nosort>Estado</th>"+ "<th class = sorttable_nosort>Foto</th>" +"</tr>"
	                + "</thead>" + "<tbody>" + "</tbody>");
			
			for (var i in lista) {
				var piso = lista[i];
				$("#tbPiso tbody").append("<tr> <td>"
                        + "<img src='icons/edit.png' class='btnEdit'/>"
                        + "<img src='icons/delete.png' class='btnDelete'/> </td>"
                        + "<td>" + piso.id + "</td>" 
                        + "<td>" + piso.idAgente + "</td>" 
                        + "<td>" + piso.precio + "</td>"
                        + "<td>" + piso.direccion 
                        + "</td>" + "<td>" + piso.ciudad + "</td>"
                        + "<td>" + piso.ano 
                        + "<td>" + piso.estado 
                        + "<td>" + piso.foto 
                        +"</td></tr>");
		}
		}
		//Lista personalizada del agente logueado
		else{
		$("#tbPiso").html("");
		$("#tbPiso").html( "<thead>" + "<tr>" + "<th class = sorttable_nosort ></th>" + "<th class = sorttable_nosort></th>"
				+ "<th class = sorttable_nosort>id</th>" + "<th class = sorttable_nosort>idAgente</th>" + "<th >Precio</th>" + "<th class = sorttable_nosort>Direccion</th>"
				+ "<th class = sorttable_nosort>Ciudad</th>" + "<th class = sorttable_nosort>Anyo</th>" + "<th class = sorttable_nosort>Estado</th>"+ "<th class = sorttable_nosort>Foto</th>" +"</tr>"
				+ "</thead>" + "<tbody>" + "</tbody>");
		for (var i in lista) {
			var piso = lista[i];
			if(piso.idAgente == agente){
				$("#tbPiso tbody").append("<tr> <td>"
						+ "<img src='icons/edit.png' class='btnEdit'/>"
						+ "<img src='icons/delete.png' class='btnDelete'/> </td>"
						+ "<td> <input type='checkbox' class = 'marcar'> </td>"
						+ "<td>" + piso.id + "</td>" 
						+ "<td>" + piso.idAgente + "</td>" 
						+ "<td>" + piso.precio + "</td>"
						+ "<td>" + piso.direccion 
						+ "</td>" + "<td>" + piso.ciudad + "</td>"
						+ "<td>" + piso.ano 
						+ "<td>" + piso.estado 
						+ "<td>" + piso.foto 
						+"</td></tr>");
			}
			
		}
	} 
	}
	this.loadPisoFromForm = function () {
		// Cogemos el Piso nuevo del formulario.
		
		var piso = {
				id : $("#id").val(),
				idAgente: localStorage.getItem('agente'),
				precio : $("#precio").val(),
				direccion : $("#direccion").val(),
				ciudad : $("#ciudad").val(),
				ano : $("#ano").val(),
				estado : $("#estado").val(),
				foto: $("#foto").val(),
		};
		return piso;
	}
	
	this.loadPisoInForm = function (piso) {
		// Pintamos los datos pisos sobre el formularios de alta/edición
		$("#id").val(piso.id);
		
		$("#idAgente").val(localStorage.getItem('agente'));
		$("#precio").val(piso.precio);
		$("#direccion").val(piso.direccion);
		$("#ciudad").val(piso.ciudad);
		$("#ano").val(piso.ano);
		$("#estado").val(piso.estado);
		$("#foto").val(piso.foto);
		//$("#iduser").focus(); // Ponemos el foco en el campo Nombre.
		//window.location.href = "http://localhost:2000/gestioneitorCliv1_0/editarPiso.html";
	}
	this.getIdPiso = function(celda) {
		// Accedemos a la fila que está por encima de esta celda
		// (closest('tr'))y despues obtenemos todas las celdas de esa fila
		// (find('tr')) y
		// nos quedamos con la segunda (get(1)) que es la contiene el "id" del
		// piso.
		var id_piso = parseInt(celda.closest('tr').find('td').get(2).innerHTML);
		return id_piso;
	}
	/*this.filtraPrecioDescendente = function(lista){
        lista.sort(function(p1, p2){
            if(p1.precio > p2.precio){
                return 1;
            }
            if(p1.precio < p2.precio){
                return -1;
            }
            if(p1.precio == p2.precio){
                return 0;
            }
        });
        that.list(lista);
    }*/
	
	
	
	this.filtraPorCiudad = function(lista, ciudad){
		$("#tbPiso").html(""); 
		$("#tbPiso").html( "<thead>" + "<tr>" + "<th></th>"
				+ "<th>id</th>" + "<th>idAgente</th>" + "<th>precio</th>" + "<th>direccion</th>"
				+ "<th>ciudad</th>" + "<th>Anyo</th>" + "<th>estado</th>"+ "<th>foto</th>" +"</tr>"
				+ "</thead>" + "<tbody>" + "</tbody>");
		for ( var i in lista) {
			var piso = lista[i];
			if(piso.ciudad.includes(ciudad)){
				$("#tbPiso tbody").append("<tr> <td>"
						+ "<img src='icons/edit.png' class='btnEdit'/>"
						+ "<img src='icons/delete.png' class='btnDelete'/> </td>"
						+ "<td>" + piso.id + "</td>" 
						+ "<td>" + piso.idAgente + "</td>" 
						+ "<td>" + piso.precio + "</td>"
						+ "<td>" + piso.direccion 
						+ "</td>" + "<td>" + piso.ciudad + "</td>"
						+ "<td>" + piso.ano 
						+ "<td>" + piso.estado 
						+ "<td>" + piso.foto 
						+"</td></tr>");
			}
		}
	}
	
}; 


function Controller(varmodel, varview) {
	// Definimos una copia de this para evitar el conflicto con el this (del
	// objeto que recibe el evento)
	// en los manejadores de eventos
	var that = this;
	// referencia al modelo
	this.model = varmodel;
	// refefencia a la vista
	this.view = varview;
	// Funcion de inicialización para cargar modelo y vista, definición de manejadores
	this.init = function() {
		// Cargamos la lista de pisos del servicio
		this.model.load();
		// Repintamos la lista de pisos.
		this.view.list(this.model.tbPisos);
		// Manejador del botón submit del formulario de Alta y Edición
		$("#frmCRUDPisos").bind("submit",
				// Método que gestiona el evento de clickar el botón submit del
				// formulario
			function(event) {
				// Si el Piso cargado en el formulario tiene ID se invoca al
				// método de 
				// edición
				// sino se invoca al método de alta.
				//alert("depura");
				piso = that.view.loadPisoFromForm();
				if ($("#id").val() == "") {
					that.model.add(piso);
				} else {
					that.model.edit(piso);
				}
				// Volvemos a listar los pisos restantes para que aparezca el
				// nuevo
				// pisos o el editado
				that.view.list(that.model.tbPisos);
			}); 
		// Manejador del enlace de edición de un Piso en la tabla
		$("#tbPiso").on("click", ".btnEdit",
				// Método que gestiona el evento de clickar en el evento
			function(event) {
				// Obtenemos el id del Piso seleccionado mediante el icono de
				// edición
				var id_piso = that.view.getIdPiso($(this));
				// Obtenemos el piso con el id_piso
				var piso = that.model.find(id_piso);
				// Cargamos el formulario con los datos del piso seleccionado para
				// editar
				that.view.loadPisoInForm(piso);
		}); 
		// Manejador del enlace de borrado de un piso 
		$("#tbPiso").on("click", ".btnDelete",
				// Método que gestiona el evento de clickar en el evento
			function(event) {
				// Obtenemos el id del piso seleccionado
				var id_piso = that.view.getIdPiso($(this));
				// Obtenemos el piso con el id_piso
				var piso = that.model.find(id_piso);
	
				that.model.remove(id_piso);
				location.reload(true);
				that.view.list(that.model.tbpisos);
		});
		/*$("#filtrarPrecio").bind("submit",
				function(event){
					//alert(document.getElementById("precioMin").value);
					var precioMin = $("#precioMin").val();
					var precioMax = $("#precioMax").val();
					//alert(precioMin);
					//document.getElementById('alert').innerHTML = 'The user input is: ' + precioMin;					
					//alert("traza");
					//console.log(precioMax);
					//alert(precioMin + " " + precioMax);
					//Parte de mostrar todos los pisos
					that.view.filtraPorPrecio(that.model.tbPisos, precioMin, precioMax);
					alert("alerta");
					//alert("llamaste al filtro");
					//that.view.list(that.model.tbPisos);
		});*/
		
		$("#filtrarCiudad").on("keyup",
				function(event){
					//alert(document.getElementById("precioMin").value);
					var ciudad = $("#ciudadFiltro").val();
					//alert(precioMin);
					//document.getElementById('alert').innerHTML = 'The user input is: ' + precioMin;					
					//console.log(precioMax);
					//alert(precioMin + " " + precioMax);
					//Parte de mostrar todos los pisos
					that.view.filtraPorCiudad(that.model.tbPisos, ciudad);
					//alert(ciudad);
					event.preventDefault();
					//alert("alerta");
					//alert("llamaste al filtro");
					//that.view.list(that.model.tbPisos);
		});
		$("#btnLimpiar").click(
				function(evento){
					//that.view.list(that.model.tbPisos);
					location.reload(true);

		});
		$("#tbPiso").on("click", ".marcar", function(){
			var id_piso = that.view.getIdPiso($(this));
            if(!this.checked) {
            	that.model.IdArray.splice(that.model.IdArray.indexOf(id_piso), 1 );
            	
            }
            else {
            	that.model.IdArray.push(id_piso);
            	
            }
		});
		
		$("#btnBorrarPisos").click(
				function(evento){
					for(i=0; i < that.model.IdArray.length; i++){
						
						that.model.remove(that.model.IdArray[i]);
						
						
					}
					
					location.reload(true);

		});
		
		
		$("#tbPiso tbody tr").mouseover(function(event){
			var valueOfTd = $(this).find('td:last-child').text();
	        var path =  valueOfTd.slice(1);
	        //alert(path);
	        var c = document.getElementById("myCanvas");
	        
	        //alert("canvas seleccionado");
	        /*if(c == null){
	        	alert("canvas  nulo");
	        }
	        alert("canvas no nulo");*/
	        var ctx = c.getContext("2d");
	        var img = new Image();
	        img.src = path;
	        ctx.drawImage(img, 0, 0, img.width, img.height, 0, 0, 300, 300);
	        //console.log("dibujado");*/
		});
		$("#tbPiso").mouseout(function(){
			//$("#tbPiso").css("background-color", "white");
			var c = document.getElementById("myCanvas");
	        var ctx = c.getContext("2d");
	        ctx.clearRect(0,0,300,300);
		});
		$("#btnImportarPisos").on("click",
				function(){
					console.log("Dentro de importar pisos");
					$.ajax({
						type: "GET",
						url: "http://localhost:8080/tew2-2022-205-211-Servicios/pisos.json",
						dataType: "json",
						success: function(pisos){
							//console.log(pisos);
							var tbPisos = localStorage.getItem("tbPiso");
							//console.log("Depura1");
							//console.log("Piso:"+pisos);
							tbPisos = JSON.parse(tbPisos);
							//console.log("depura2");
							//console.log(tbPisos);
							/*if(tbPisos == null){
	                            alert("No deberías estar aquí.")
								tbPisos = [];
							}*/
							//console.log(tbPisos);
							for (var i in pisos){
								var piso = JSON.stringify({
	                                 id : pisos[i].ID,
	                                 idAgente : localStorage.getItem('agente'),
	                                 precio : pisos[i].Precio,
	                                 direccion : pisos[i].Direccion,
	                                 ciudad : pisos[i].Ciudad,
	                                 ano : pisos[i].Anyo,
	                                 estado : pisos[i].Estado,
	                                 foto : pisos[i].Foto,
	                             });
								 //console.log("Piso: "+piso);
								 if(that.model.find(pisos[i].ID)!=null){
	                                 console.log("YA HAY UN PISO CON ESE ID");
	                                 that.model.edit(piso);
	                             }
	                             else{
	                                 that.model.add(piso);
	                             }
							}
	                         //console.log(tbPisos);
							that.view.list(that.model.tbPisos);
					}});
		});
	}; 
};

$(function() {
	// Creamos el modelo con los datos y la conexión al servicio web.
	var model = new Model();
	// Creamos la vista que incluye acceso al modelo. 
	var view = new View();
	// Creamos el controlador
	var control = new Controller(model, view);
	// Iniciamos la aplicación
	control.init();
}); 







