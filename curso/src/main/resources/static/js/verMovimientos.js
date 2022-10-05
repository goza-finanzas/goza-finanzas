if(localStorage.email == "" && localStorage.token == ""){
    alert("Por Favor Inicie Sesión");
    document.getElementById('table').remove();
    document.querySelector('h2').outerHTML = '<h2>Por Favor <a href="login.html">Inicie Sesión</a><h2>';
} else {
    cargarUsuarios();
};

async function cargarUsuarios() {
    const request = await fetch('movimiento', {
    method: 'GET',
    headers: getHeaders()
  });

  const movimientos = await request.json();

  let listadoHtml = '';

    for (let movimiento of movimientos) {
        let movimientoHtml = `
            <tr>
                <td>${movimiento.id}</td>
                <td>${movimiento.nombre}</td>
                <td>${movimiento.fecha}</td>
                <td>${movimiento.monto}</td>
                <td>${movimiento.categoria}</td>
                <td>${movimiento.descripcion}</td>
                <td>
                <button class="btn btn-warning" onclick='actualizarMovimiento(${JSON.stringify(movimiento)})'>Actualizar</button>
                &nbsp;
                <button class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#exampleModal" onclick='eliminarMovimiento(${movimiento.id})'>Eliminar</button>
                </td>
            </tr>`;
        listadoHtml += movimientoHtml;
    }

    tbody.innerHTML = listadoHtml

}

function getHeaders() {
    return {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Correo': localStorage.email,
        'Authorization': localStorage.token
    };
}

async function eliminarMovimiento(id) {
    // Bueno aquí con confirm nos genera un letrero y con ! le decimos que lo contrario
    // debido a que si es falso solamente return nada y no haga el resto
    // Mientras que si es verdadero debe hacer el fetch
    if(!confirm('¿Desea eliminar este movimiento?')) {
        return;
    }
    const peticion = await fetch('movimiento/', {
        method: 'DELETE',
        headers: getHeaders()
    ,
    //JSON.stringify lo que hace es coger cualquier objeto de javascript
    //y lo convierte a un string de JSON
        body: id
    });
    //recargar la url
    location.reload()
}

function actualizarMovimiento(movimiento) {
    localStorage.movimientos = JSON.stringify(movimiento);
    window.location.href = "actualizarMovimientos.html";
}
