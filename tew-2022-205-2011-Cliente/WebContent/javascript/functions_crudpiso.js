//$(function() {
//Creamos el modelo con los datos y la conexión al servicio web.
//var model = new Model();
//model.load();
//}) 


//Clase que contiene el Modelo de la aplicación.
function Model(){
	//Lista de pisos.
	this.tbPisos = null;
	// Lista de pisos a eliminar
	this.tbPisosEliminar = [];
	//this.tbIDAgente = localStorage.getItem('agente');

	//Carga los datos del servicio sobreescribiendo el dato this.tbAlumnos.
	this.load = function() {
		// Cargamos los pisos
		this.tbPisos = PisosServicesRs.getPisos();//getPisosAgente(localStorage.getItem('agente'));
		// Rellenamos la tabla de IDs con "-1" para indicar que no están marcados
		//this.tbPisosEliminar.length = this.tbPisos.length;
	}


	//Llamamos al servicio de alta de piso
	this.add = function(piso) {
		//	Llamamos al servicio de alta de piso
		PisosServicesRs.savePiso({
			$entity : piso,
			$contentType : "application/json"
		});
		//	Recargamos la lista de pisos.
		this.load();
	}


	//Actualización de un piso existente: PENDIENTE DE IMPLEMENTAR
	this.edit = function(piso) {
		//	Llamamos al servicio de alta de piso
		PisosServicesRs.updatePiso({
			$entity : piso,
			$contentType : "application/json"
		});
		//	Recargamos la lista de pisos.
		this.load();
	} 


	//Eliminación un piso existente
	this.remove = function(idPiso) {
		//	Llamamos al servicio de borrado de piso
		PisosServicesRs.deletePiso({
			id : idPiso
		});
		//	Recargamos la lista de pisos.
		this.load();
	}


	//Encontrar un piso existente
	this.find = function(idPiso) {
		function checkPiso(obj) {
			return obj.ID == idPiso;
		}
		//	Buscamos los datos del piso cuyo id_piso sea el mismo que el
		//	seleccionado
		var piso = this.tbPisos.find(checkPiso);
		return piso;
	} 
	
	
	// Reiniciar la base de datos
	this.reiniciarBBDD = function() {
		// Llamamos al servicio de borrado de piso
		PisosServicesRs.reiniciarBBDD();
		//	Recargamos la lista de pisos.
		this.load();
	}

}; 


//Clase que contiene la gestión de la capa Vista
function View(){

	// Obtenemos el ID del piso
	//var IDAgente = localStorage.getItem('agente');

	this.list = function (lista) {
		$("#tblList").html("");
		$("#tblList").html( "<thead>" + "<tr>" + "<th></th>"
				+ "<th>ID</th>" + "<th>IDAgente</th>" + "<th>Precio</th>"
				+ "<th>Direccion</th>" + "<th>Ciudad</th>" + "<th>Año</th>"
				+ "<th>Estado</th>" + "<th>Foto</th>" +"</tr>"
				+ "</thead>" + "<tbody>" + "</tbody>");
		for ( var i in lista) {
			var piso = lista[i];
			console.log(localStorage.getItem("agente"));
			if(piso.IDAgente == localStorage.getItem("agente"))
			{
				$("#tblList tbody").append("<tr> <td>"
						+ "<img src='icons/edit.png' class='btnEdit'/>"
						+ "<img src='icons/delete.png' class='btnDelete'/>"
						+ "<input type='checkbox' id='checkbox' class='btnCheckbox'/> </td>"
						+ "<td>" + piso.ID + "</td>" + "<td>" + piso.IDAgente + "</td>"
						+ "<td>" + piso.precio + "</td>" + "<td>" + piso.direccion + "</td>"
						+ "<td>" + piso.ciudad + "</td>" + "<td>" + piso.año + "</td>"
						+ "<td>" + piso.estado + "</td>" + "<td>" + piso.foto + "</td></tr>");
				//+ "<td>" + "<canvas width='300' height='300' id='canvas'> <img src='/gestioneitorCli1_0/"+ piso.foto + "' width='300' height='300'/></canvas></tr>");
				//+ "<td>" + piso.foto + "</td></tr>");
				//+ "<input type='checkbox' id='checkbox' class='checkbox'/> </td>"

				/*var id = $(this).attr('id');
			    console.log(id);
			    var canvas = $(this).find("canvas")[0];
			    var context = canvas.getContext("2d");
			    var imagen = new Image;
			    imagen.onload = function() {
			    	context.drawImage(this, 0, 0);
			    };
			    imagen.src = "http://localhost:2000/gestioneitorCli1_0/" + piso.foto;*/
			}

		}
	}


	this.listPublico = function (lista) {
		$("#tblListPublico").html("");
		$("#tblListPublico").html( "<thead>" + "<tr>" + "<th></th>"
				+ "<th class='no-sort'>ID</th>" 
				+ "<th id='thPrecio'>Precio</th>"
				+ "<th class='no-sort'>Direccion</th>" 
				+ "<th class='no-sort'>Ciudad</th>" 
				+ "<th class='no-sort'>Año</th>"
				+ "<th class='no-sort'>Estado</th>" 
				+ "</tr>" + "</thead>" + "<tbody>" + "</tbody>");
		for (var i in lista) {
			var piso = lista[i];
			//if(piso.IDAgente == localStorage.getItem("agente"))
			//{
				$("#tblListPublico tbody").append("<tr> <td>"
						+ "<td>" + piso.ID + "</td>"
						+ "<td>" + piso.precio + "</td>" + "<td>" + piso.direccion + "</td>"
						+ "<td id='tdCiudad'>" + piso.ciudad + "</td>" + "<td>" + piso.año + "</td>"
						+ "<td>" + piso.estado + "</td></tr>");
			//}

		}
	}

	this.loadPisoFromForm = function () {
		// Cogemos el piso nuevo del formulario.
		var piso = {
				ID : $("#id").val(),
				IDAgente : localStorage.getItem("agente"),
				//IDAgente : $("#idAgente").val(),
				precio : $("#precio").val(),
				direccion : $("#direccion").val(),
				ciudad : $("#ciudad").val(),
				año : $("#año").val(),
				estado : $("#estado").val(),
				foto : $("#foto").val()
		};
		return piso;
	}

	this.loadPisoInForm = function (piso) {
		// Pintamos los datos pisos sobre el formularios de alta/edición
		//console.log(piso.ID);
		$("#id").val(piso.ID);
		$("#idAgente").val(localStorage.getItem("agente"));
		//$("#idAgente").val(piso.IDAgente);
		$("#precio").val(piso.precio);
		$("#direccion").val(piso.direccion);
		$("#ciudad").val(piso.ciudad);
		$("#año").val(piso.año);
		$("#estado").val(piso.estado);
		$("#foto").val(piso.foto);
		//$("#id").focus(); // Ponemos el foco en el campo Nombre. 
	} 

	this.getIdPiso = function(celda) {
		// Accedemos a la fila que está por encima de esta celda
		// (closest('tr'))y despues obtenemos todas las celdas de esa fila
		// (find('tr')) y
		// nos quedamos con la segunda (get(1)) que es la contiene el "id" del
		// piso.
		var idPiso = parseInt(celda.closest('tr').find('td').get(1).innerHTML);
		return idPiso;
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
		this.view.listPublico(this.model.tbPisos);

		// Hacemos que la tabla sea responsiva
		$("#tblListPublico").DataTable({
			searching: false, 
			paging: false, 
			info: false, 
			orderable: true,
			"ordering":true,
			responsive:true,

			columnDefs: [{
				orderable: false,
				sortable: false,
				"ordering":false,
				targets: "no-sort"
			}]
		} );


		/*$('#tblListPublico #thPrecio').each(function(index, th) {
			  $(th).unbind('click');
			  $(th).append('<button class="sort-btn btn-asc">&#9650;</button>');
			  $(th).append('<button class="sort-btn btn-desc">&#9660;</button>');

			  $(th).find('.btn-asc').click(function() {
				  //console.log($(this).parent().parent().parent().parent());
				  $(this).parent().parent().parent().parent().DataTable().column(index).order('asc').draw();
			  }); 
			  $(th).find('.btn-desc').click(function() {
				  //console.log($(this).parent().parent().parent().parent());
				  $(this).parent().parent().parent().parent().DataTable().column(index).order('desc').draw();      
			  }); 
			}); */ 




		// Mantenemos el area de la imagen fija
		/*$(window).scroll(function() {    
			var altura_del_header = $("headerListado").outerHeight(true);
			var altura_del_menu = $("menuListado").outerHeight(true);

			if ($(window).scrollTop() >= altura_del_header){
				$("menuListado").addClass("fixed");
				$("contenidoListado").css("margin-top", (altura_del_menu) + "px");
			} else {
				$("menuListado").removeClass("fixed");
				$("contenidoListado").css("margin-top", "0");
			}
		});*/


		// MANEJADORES DE EVENTOS

		// Manejador del botón submit del formulario de Alta y Edición
		$("#frmCRUDPisos").bind("submit",
				// Método que gestiona el evento de clickar el botón submit del
				// formulario
				function(event) {
			// Si el piso cargado en el formulario tiene ID se invoca al
			// método de
			// edición
			// sino se invoca al método de alta.
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

		// Manejador del enlace de edición de un piso en la tabla
		$("#tblList").on("click", ".btnEdit",
				// Método que gestiona el evento de clickar en el evento
				function(event) {
			// Obtenemos el id del piso seleccionado mediante el icono de
			// edición
			var idPiso = that.view.getIdPiso($(this));
			//console.log(idPiso);
			// Obtenemos el piso con el id_piso
			var piso = that.model.find(idPiso);
			//console.log(piso.ID);
			// Cargamos el formulario con los datos del piso seleccionado para
			// editar
			that.view.loadPisoInForm(piso);
		});


		// Manejador del enlace de eliminación de un piso en la tabla
		$("#tblList").on("click", ".btnDelete",
				// Método que gestiona el evento de clickar en el evento
				function(event) {
			// Obtenemos el id del piso seleccionado mediante el icono de
			// edición
			var idPiso = that.view.getIdPiso($(this));
			// Obtenemos el piso con el id_piso
			var piso = that.model.find(idPiso);
			// Cargamos el formulario con los datos del piso seleccionado para
			// editar
			that.model.remove(piso.ID);
			// Volvemos a listar los pisos restantes para que aparezca el
			// nuevo
			// pisos o el editado
			that.view.list(that.model.tbPisos);
		});
		
		
		// Manejador del botón de limpiar los datos
		$("#btnLimpiarRegistros").on("click",
				// Método que gestiona el evento de clickar el botón submit del
				// formulario
				function(event) {
			$("#id").val("");
			$("#idAgente").val("");
			$("#precio").val("");
			$("#direccion").val("");
			$("#ciudad").val("");
			$("#año").val("");
			$("#estado").val("");
			$("#foto").val("");
		});



		// Manejador del evento sobre el posado de una fila de la tabla
		// para mostrar la imagen del piso en cuestión
		$("#tblListPublico tbody tr").mouseover(
				// Método que gestiona el evento de clickar en el evento
				function(event) {
					// Primero, debemos obtener la fila en la que se produce el evento
					// Para ello, el objeto this nos devuelve un objeto tr (fila) y nosotros queremos un campo td (columna) que es el ID
					var idPiso = that.view.getIdPiso($(this));
					console.log(idPiso);
					// Obtenemos el piso con el idPiso
					var piso = that.model.find(idPiso);
					console.log(piso.foto);

					// Creamos la variable que contendrá el canvas que guardara la imagen
					var canvas = document.getElementById("canvas");
					//if(canvas != null){
					// Creamos el contexto del canvas
					var context = canvas.getContext("2d");
					//context.msImageSmoothingEnabled = false;
					//context.mozImageSmoothingEnabled = false;
					//context.webkitImageSmoothingEnabled = false;
					//context.imageSmoothingEnabled = false;
					// Creamos la variable que almacenará la imagen
					var imagen = new Image();
					// Cargamos la imagen en la variable
					//imagen.src = "http://localhost:2000/gestioneitorCli1_0/" + piso.foto;
					//imagen.src = "album/" + piso.foto;
					imagen.src = "http://localhost:2000/gestioneitorCli1_0/" + piso.foto;
					console.log(imagen.src);
					// Una vez definida la imagen, la dibujamos en el canvas
					context.drawImage(imagen, 0, 0, imagen.width, imagen.height, 0, 0, 300, 300);
					//}
				});

		// Manejador del evento sobre el posado de una fila de la tabla
		// para quitar la imagen del piso anterior
		$("#tblListPublico tbody tr").mouseout(
				// Método que gestiona el evento de clickar en el evento
				function(event) {
					// Creamos la variable que contendrá el canvas que guardara la imagen
					var canvas = document.getElementById("canvas");
					if(canvas != null){
						// Creamos el contexto del canvas
						var context = canvas.getContext("2d");
						// Una vez definida el contexto, borramos el contenido del canvas con el clearRect
						context.clearRect(0, 0, 300, 300);
					}
				});




		// Manejador del botón submit encargado del borrado múltiple de pisos
		$("#listado").bind("submit",
				// Método que gestiona el evento de clickar el botón submit del
				// formulario
				function(event) {

			// Los mostramos por consola
			console.log("Se van a borrar " + that.model.tbPisosEliminar.length + " pisos");
			// Los recorremos todos para obtener su ID
			for(i=0; i<that.model.tbPisosEliminar.length; i++){
				// Obtenemos el piso
				var piso = that.model.find(that.model.tbPisosEliminar[i]);
				console.log("Se está borrando el piso " + piso.ID);
				// Eliminamos el piso
				that.model.remove(piso.ID);
			}
			// Volvemos a listar los pisos restantes para que aparezca el
			// nuevo
			// pisos o el editado
			that.view.list(that.model.tbPisos);
		}); 


		// Manejador del checkbox del borrado múltiple de pisos
		$("#tblList tbody tr #checkbox").change(
				// Método que gestiona el evento de cambio en el checkbox
				function() {

					// Obtenemos el ID del piso
					var idPiso = that.view.getIdPiso($(this));
					console.log("Se ha seleccionado el piso " + idPiso);
					console.log("¿Checkbox marcado? " + this.checked);

					// Si el checkbox esta seleccionado  
					if(this.checked)
					{
						// lo añadimos a la lista de IDs de eliminación
						that.model.tbPisosEliminar.push(idPiso);
						console.log("Piso " + idPiso + " añadido a la lista de IDs");
					}
					// Si por el contrario, el checkbox se deselecciona
					else
					{
						// Observamos donde se encuentra el piso en la lista de IDs
						var indexIDPiso = that.model.tbPisosEliminar.indexOf(idPiso);
						// En caso de encontrarlo 
						if (indexIDPiso !== -1) {
							// Borramos el piso de la lista de eliminar
							that.model.tbPisosEliminar.splice(indexIDPiso, 1);
							console.log("Piso " + idPiso + " eliminado de la lista de IDs");
						}
					}
				});


		// Manejador de la barra de búsqueda de la lista de pisos
		$("#barraBusqueda").keyup(function(){
			_this = this;
			// Buscamos en cada fila de la tabla
			$.each($("#tblListPublico tbody tr"), function() {
				// En caso de encontrar resultados coincidentes
				if($(this).find("#tdCiudad").text().toLowerCase().indexOf($(_this).val().toLowerCase()) === -1)
				{
					// Mostramos la fila en cuestión del piso
					$(this).hide();
				}
				// En caso de no coincidir con la búsqueda
				else
				{
					// No mostramos la fila en cuestión del piso
					$(this).show();
				}
			});
		});



		// Manejador del botón de importación de pisos
		$("#btnImportacion").on("click",
				// Método que gestiona el evento de clickar el botón submit del
				// formulario
				function() {

			$.ajax({
				url : "http://localhost:8080/gestioneitorv2_0/pisos.json",
				type : "GET",
				dataType : "json",
				// Listado de pisos (función de callback)
				success : function(pisos) {
					var tbPisos = localStorage.getItem("tbPisos");
					tbPisos = JSON.parse(tbPisos);
					//alert("Recibida respuesta con exito!");

					//Para acceder a los datos de los pisos se puede recorrer así
					for (var i in pisos) {
						//Preparamos el registro de un piso
						var piso = JSON.stringify({
							ID : pisos[i].ID,
							IDAgente : localStorage.getItem("agente"),
							precio : pisos[i].Precio,
							direccion : pisos[i].Direccion,
							ciudad : pisos[i].Ciudad,
							año : pisos[i].Anyo,
							estado : pisos[i].Estado,
							foto : pisos[i].Foto
						}); // : localStorage.getItem('agente');
						//… … …
						piso = JSON.parse(piso);
						
						//console.log(piso.ID+", "+piso.direccion+", "+piso.foto+", "+piso.año+", ");
						// Buscamos el piso previamente en la BBDD
						var busqueda = that.model.find(piso.ID);
						// En caso de que el piso no exista ya,
						if(busqueda == null)
						{
							// lo añadimos
							that.model.add(piso);
							console.log("that.model.add(piso); "+piso.ID);
						}
						// En caso de que el piso ya exista,
						else
						{
							// lo sobreescribimos
							that.model.edit(piso);
							console.log("that.model.edit(piso); "+piso.ID);
						}
						
					}
					//… …
					// Actualizamos el modelo y la vista
					//… … …
					that.view.list(that.model.tbPisosImport);
					// Remitir piso al servidor vía servicios web
					//……
				} //Cierre de la función de callback
			}); //Cierre del parámetro de .ajax
		});
		
		
		
		// Manejador del botón de reinicio de la base de datos
		$("#btnReiniciarBBDD").on("click",
				// Método que gestiona el evento de clickar el botón submit del
				// formulario
				function() {
			// Reiniciamos la base de datos con los datos originales
			that.model.reiniciarBBDD();
			// Volvemos a listar los pisos restantes para que aparezca el
			// nuevo
			// pisos o el editado
			that.view.list(that.model.tbPisos);
		});

	}
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
