if(localStorage.email == "" && localStorage.token == ""){
    alert("Por Favor Inicie Sesión");
    document.getElementById('form').remove();
    document.querySelector('#section-form').innerHTML = '<h2>Por Favor <a href="login.html">Inicie Sesión</a><h2>';
}

if(localStorage.movimientos) {
    const movimiento = JSON.parse(localStorage.movimientos);
    llenarCampos(movimiento)
}

function llenarCampos(movimiento) {
    const form = document.getElementById("form");
    form.nombre.value = movimiento.nombre;
    form.fecha.value = movimiento.fecha;
    form.monto.value = movimiento.monto;
    form.categoria.value = movimiento.categoria;
    form.descripcion.value = movimiento.descripcion;
}



async function actualizarMovimientos(evt) {
    evt.preventDefault()
    const movimiento = JSON.parse(localStorage.movimientos);
    localStorage.removeItem("movimientos");
    const form = evt.target
    
    let datos = {};
    datos.id = movimiento.id;
    datos.usuarios_id = movimiento.usuarios_id;
    datos.nombre = form.nombre.value ;
    datos.fecha = form.fecha.value ;
    datos.monto = form.monto.value ;
    datos.categoria = form.categoria.value ;
    datos.descripcion = form.descripcion.value ;



    const request = await fetch('movimiento', {
        method: 'PUT',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Correo': localStorage.email,
            'Authorization': localStorage.token
        },
        //JSON.stringify lo que hace es coger cualquier objeto de javascript
        //y lo convierte a un string de JSON
        body: JSON.stringify(datos)
    });
    alert("El movimiento fue actualizado con éxito!");
    
    window.location.href = "verMovimientos.html"
}



