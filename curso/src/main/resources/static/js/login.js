async function iniciarSesion(evt) {
    evt.preventDefault()

    const form = evt.target;

    let datos = {};
    datos.email = form.txtEmail.value;
    datos.password = form.txtPassword.value;

    const request = await fetch('api/login', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
    },
        //JSON.stringify lo que hace es coger cualquier objeto de javascript
        //y lo convierte a un string de JSON
        body: JSON.stringify(datos)
    });

    const respuesta = await request.text();

    if (respuesta == "FAIL") {
    //para guardar el token es en el almacenamiento local
        alert("Las Credenciales son incorrectas. Por favor intente nuevamente");
    }else {
        localStorage.token = respuesta;
        localStorage.email = datos.email;
        window.location.href = 'index.html'
    }
}
