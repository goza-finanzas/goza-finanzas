if(localStorage.email == "" && localStorage.token == ""){
    alert("Por Favor Inicie Sesión");
    document.getElementById('form').remove();
    document.querySelector('#section-form').innerHTML = '<h2>Por Favor <a href="login.html">Inicie Sesión</a><h2>';
}

async function registrarMovimientos(evt) {
    evt.preventDefault()
    const form = evt.target
    
    let datos = {};
    alert(form.nombre.value);
    datos.nombre = form.nombre.value ;
    datos.fecha = form.fecha.value ;
    datos.monto = form.monto.value ;
    datos.categoria = form.categoria.value ;
    datos.descripcion = form.descripcion.value ;


    const request = await fetch('movimiento', {
        method: 'POST',
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
    alert("El movimiento fue creado con éxito!");
    
    clear(form);
}

function clear(form){
    form.nombre.value = "";
    form.fecha.value = "";
    form.monto.value = "";
    form.categoria.value = "";
    form.descripcion.value = "";
}

