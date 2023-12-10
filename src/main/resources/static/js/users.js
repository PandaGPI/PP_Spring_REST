const allUsersURL = 'http://localhost:7070/api/users'

showAllUsers()

function showAllUsers() {
    console.log("showAllUsers activated")
    fetch(allUsersURL)
        .then(response => response.json())
        .then(result => {
            for (let user of result) {
                let temp = `<tr id="row-${user.id}">
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.surName}</td>
                    <td>${user.age}</td> 
                    <td>${user.mail}</td>
                    <td>${user.rolesToString}</td>
                    <td>
                        <button class="btn btn-info" onclick=showEditUser(${user.id}) type="button" data-toggle="modal" data-target=#modalEditView>Edit</button>
                    </td>
                    <td>
                        <button class="btn btn-danger" onclick="showDeleteUser(${user.id})" type="button" data-toggle="modal" data-target=#modalDeleteView>Delete</button>
                    </td>
                </tr>`
                $('#mainTableBodyUsers').append(temp)
            }
        })
}

const reloadButton = document.querySelector("button#btnEdit");
reloadButton.addEventListener("click", () => {
    // Reload the current page, including the JavaScript files.
    location.reload();
});
