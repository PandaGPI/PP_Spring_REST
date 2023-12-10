
function showDeleteUser(userId) {
    const userByIdURL = `http://localhost:7070/api/user/${userId}`
    fetch(userByIdURL)
        .then(response => response.json())
        .then(user => {
            $('#userIdDelete').attr('value', `${user.id}`)
            $('#userNameDelete').attr('value', `${user.name}`)
            $('#userLastnameDelete').attr('value', `${user.surName}`)
            $('#userAgeDelete').attr('value', `${user.age}`)
            $('#userEmailDelete').attr('value', `${user.mail}`)
            $('#userPasswordDelete').attr('value', `${user.password}`)
            $('#userRoleDelete').attr('value', `${user.rolesToString}`)
            $('#btnDelete').attr('onclick', `deleteUser(${user.id})`)
        })
}

function deleteUser(userId){
    const userByIdURL = `http://localhost:7070/api/delUser/${userId}`
    console.log('deleteUser activated')
    fetch(userByIdURL,{
        method: 'DELETE'
    })
    $(`#row-${userId}`).remove()
}