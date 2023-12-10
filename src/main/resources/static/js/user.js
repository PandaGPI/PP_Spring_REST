const showUserURL = 'http://localhost:7070/api/user';

fetch(showUserURL)
    .then(response => response.json())
    .then(user => {
        $('#footerEmail').append(`${user.mail}`)
        $('#footerRoles').append(`${user.rolesToString}`)
        let temp = `<tr>
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.surName}</td>
                    <td>${user.age}</td>
                    <td>${user.mail}</td>
                    <td>${user.rolesToString}</td>
                </tr>`
        $('#mainTableBodyUser').append(temp)
    })
