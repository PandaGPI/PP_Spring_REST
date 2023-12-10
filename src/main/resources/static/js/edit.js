function showEditUser(userId) {
    console.log(userId)
    const userByIdURL = `http://localhost:7070/api/user/${userId}`
    fetch(userByIdURL)
        .then(response => response.json())
        .then(user => {
            $('#userIdEdit').attr('value', `${user.id}`)
            $('#userNameEdit').attr('value', `${user.name}`)
            $('#userLastnameEdit').attr('value', `${user.surName}`)
            $('#userAgeEdit').attr('value', `${user.age}`)
            $('#userEmailEdit').attr('value', `${user.mail}`)
            $('#userPasswordEdit').attr('value', `${user.password}`)
            $('#userRoleEdit').attr('value', `${user.rolesToString}`)
            $('#btnEdit').attr('onclick', `editUser()`)
        })
}

function editUser() {
    const editUserURL = 'http://localhost:7070/api/saveOrUpdate'
    console.log("editUser activated")
    fetch(editUserURL, {
        method: 'POST',
        headers: {'Content-type': 'application/json; charset=UTF-8'},
        body: JSON.stringify({
            id: $('#userIdEdit').val(),
            name: $('#userNameEdit').val(),
            surName: $('#userLastnameEdit').val(),
            age: parseInt($('#userAgeEdit').val()),
            mail: $('#userEmailEdit').val(),
            password: $('#userPasswordEdit').val(),
            roles: [
                {

                    id: parseInt($('#userRoleEdit').val()[0])

                }
            ]
        })
    })
}