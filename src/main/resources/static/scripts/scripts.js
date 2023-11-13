window.onload = async function () {
    await fetchBoardGames();
    await fetchVideoGames();

    //console.log("onload() ended");

    let addBoardGameButton = document.querySelector("button#add-bg");
    addBoardGameButton.onclick = addNewBoardGame;
    let addVideoGameButton = document.querySelector("button#add-vg");
    addVideoGameButton.onclick = addNewVideoGame;

    let editVideoGameButton = document.querySelector("button#edit-vg");
    editVideoGameButton.onclick = editVideoGame;

    let editBoardGameButton = document.querySelector("button#edit-bg");
    editBoardGameButton.onclick = editBoardGame;

    let deleteVGLinks = document.querySelectorAll("#vg-tbody")
    for (let i = 0; i < deleteVGLinks.length; i++){
        deleteVGLinks[i].onclick = deleteVGHandler
    }

    let deleteBGLinks = document.querySelectorAll("#bg-tbody")
    for (let i = 0; i < deleteBGLinks.length; i++){
        deleteBGLinks[i].onclick = deleteBGHandler
    }

}

function deleteVGHandler(event)
{
    event.preventDefault()

    let row = event.target.parentElement.parentElement
    let tbody = document.querySelector("#vg-tbody")
    tbody.removeChild(row)
}

function deleteBGHandler(event)
{
    event.preventDefault()

    let row = event.target.parentElement.parentElement
    let tbody = document.querySelector("#bg-tbody")
    tbody.removeChild(row)
}

async function fetchBoardGames() {
    let uri = "http://localhost:8080/boardgames"
    let config = {
        method: "get"
    };

    let response = await fetch(uri, config);
    let json = await response.json();
    addBoardGamesToTable(json)
}

async function fetchVideoGames() {
    let uri = "http://localhost:8080/videogames"
    let config = {
        method: "get"
    };

    let response = await fetch(uri, config);
    let json = await response.json();
    addVideoGamesToTable(json)
    editVideoGame(json)
}

function addSingleBoardGameToTable(boardgame) {
    //create HTML elements
    let body = document.querySelector("#bg-tbody");
    let row = document.createElement("tr");
    let tableId = document.createElement("td");
    let tableName = document.createElement("td");
    let tableCategory = document.createElement("td");
    let tableMin = document.createElement("td");
    let tableMax = document.createElement("td");
    let deleteLinkCell = document.createElement("td")
    let deleteLink = document.createElement("a");

    //connect them (parent to child)
    body.appendChild(row);
    row.appendChild(tableId)
    row.appendChild(tableName)
    row.appendChild(tableCategory)
    row.appendChild(tableMin)
    row.appendChild(tableMax)
    deleteLinkCell.appendChild(deleteLink)
    row.appendChild(deleteLinkCell)


    //add text or HTML attributes
    tableId.textContent = boardgame.id;
    tableName.textContent = boardgame.name;
    tableCategory.textContent = boardgame.category;
    tableMin.textContent = boardgame.minPlayers;
    tableMax.textContent = boardgame.maxPlayers;
    deleteLink.textContent = "delete"
    deleteLink.href = "#"
}

function addSingleVideoGameToTable(videogame) {
    //create HTML elements
    let tBody = document.querySelector("#vg-tbody");
    let row = document.createElement("tr");
    let tableId = document.createElement("td");
    let tableName = document.createElement("td");
    let tableConsole = document.createElement("td");
    let tableMulti = document.createElement("td");
    let deleteLinkCell = document.createElement("td")
    let deleteLink = document.createElement("a");

    //connect them (parent to child)
    tBody.appendChild(row);
    row.appendChild(tableId)
    row.appendChild(tableName)
    row.appendChild(tableConsole)
    row.appendChild(tableMulti)
    deleteLinkCell.appendChild(deleteLink)
    row.appendChild(deleteLinkCell)

    //add text or HTML attributes
    tableId.textContent = videogame.id;
    tableName.textContent = videogame.name;
    tableConsole.textContent = videogame.consoleType;
    tableMulti.textContent = videogame.multiplayer;
    deleteLink.textContent = "delete"
    deleteLink.href = "#"
}

function addBoardGamesToTable(boardGameArray) {
    for (let i = 0; i < boardGameArray.length; i++) {
        let boardgame = boardGameArray[i];
        addSingleBoardGameToTable(boardgame);
    }
}

function addVideoGamesToTable(videoGameArray) {
    for (let i = 0; i < videoGameArray.length; i++) {
        let videogame = videoGameArray[i];
        addSingleVideoGameToTable(videogame);
    }
}

async function addNewBoardGame(event) {
    //stop the form from submitting, we are using fetch() instead!
    event.preventDefault();

    let minInput = document.querySelector("input#bg-min").value;
    let maxInput = document.querySelector("input#bg-max").value;

    if (minInput > 0 && minInput <= 20 && maxInput > 0 && maxInput <= 20) {
        let newBoardGame = {
            name: document.querySelector("input#bg-name").value,
            category: document.querySelector("input#bg-category").value,
            minPlayers: minInput,
            maxPlayers: maxInput
        };

        let uri = "http://localhost:8080/boardgames";
        let config = {
            method: "post",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(newBoardGame)
        };

        let response = await fetch(uri, config);
        let jsonObjectReturned = await response.json();
        addSingleBoardGameToTable(jsonObjectReturned);
    } else {
        alert("Please enter a number between 1 - 20")
    }
}

async function addNewVideoGame(event) {
    event.preventDefault();

    let multiplayerInput = document.querySelector("input#vg-multi").value;

    //added validation for boolean field
    if (multiplayerInput === "true" || multiplayerInput === "false") {

        let newVideoGame = {
            name: document.querySelector("input#vg-name").value,
            consoleType: document.querySelector("input#vg-console").value,
            multiplayer: multiplayerInput,
        };

        let uri = "http://localhost:8080/videogames";
        let config = {
            method: "post",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(newVideoGame)
        };

        let response = await fetch(uri, config);
        let jsonObjectReturned = await response.json();

        addSingleVideoGameToTable(jsonObjectReturned);
    } else {
        alert("Please enter 'true' or 'false' for Multiplayer.");
    }
}

async function editVideoGame(event) {
    event.preventDefault();

    let editedVideoGame = {
        id: document.querySelector("input#edit-vg-id").value,
        consoleType: document.querySelector("input#edit-vg-console").value,
        name: document.querySelector("input#edit-vg-name").value,
        multiplayer: document.querySelector("input#edit-vg-multi").value
    };

    let uri = "http://localhost:8080/videogames";
    let config = {
        method: "put",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(editedVideoGame)
    };

    let response = await fetch(uri, config);
    let jsonObjectReturned = await response.json();

    let rows = document.querySelectorAll("#vg-tbody tr");
    let gameId = jsonObjectReturned.id

    for (let i = 0; i < rows.length; i++) {
        let tr = rows[i]

        let tdId = tr.children[0];
        let otherId = parseInt(tdId.textContent)

        if (gameId === otherId) {
            tr.children[1].textContent = jsonObjectReturned.name
            tr.children[2].textContent = jsonObjectReturned.consoleType
            tr.children[3].textContent = jsonObjectReturned.multiplayer
        }
    }
}

async function editBoardGame(event) {
    event.preventDefault();

    let editedBoardGame = {
        id: document.querySelector("input#edit-bg-id").value,
        name: document.querySelector("input#edit-bg-name").value,
        category: document.querySelector("input#edit-bg-category").value,
        minPlayers: document.querySelector("input#edit-bg-min").value,
        maxPlayers: document.querySelector("input#edit-bg-max").value
    };

    let uri = "http://localhost:8080/boardgames";
    let config = {
        method: "put",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(editedBoardGame)
    };

    let response = await fetch(uri, config);
    let jsonObjectReturned = await response.json();

    let rows = document.querySelectorAll("#bg-tbody tr");
    let gameId = jsonObjectReturned.id

    for (let i = 0; i < rows.length; i++) {
        let tr = rows[i]

        let tdId = tr.children[0];
        let otherId = parseInt(tdId.textContent)

        if (gameId === otherId) {
            tr.children[1].textContent = jsonObjectReturned.name
            tr.children[2].textContent = jsonObjectReturned.category
            tr.children[3].textContent = jsonObjectReturned.minPlayers
            tr.children[4].textContent = jsonObjectReturned.maxPlayers
        }
    }
}
