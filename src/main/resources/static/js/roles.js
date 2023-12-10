const allRoles = 'http://localhost:7070/api/roles'

fetch(allRoles)
    .then(response => response.json())
    .then(roles =>{
        for(role of roles) {
            let temp = `
            <option value=${role.id}>
                ${role.role}
            </option>`
            $('#userRoleEdit').append(temp)
            $('#userRoleDelete').append(temp)
            $('#newUserRole').append(temp)
        }
    })