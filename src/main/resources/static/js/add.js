
function addNewUser() {
    const addUserURL = 'http://localhost:7070/api/saveOrUpdate'
    console.log("addNewUser activated")
    fetch(addUserURL, {
        method: 'POST',
        headers: {'Content-type': 'application/json; charset=UTF-8'},
        body: JSON.stringify({
            name: $('#newUserName').val(),
            surName: $('#newUserLastname').val(),
            age: $('#newUserAge').val(),
            mail: $('#newUserEmail').val(),
            password: $('#newUserPassword').val(),
            roles: [
                {
                    id: parseInt($('#newUserRole').val()[0])
                }
            ]
        })
    })
}