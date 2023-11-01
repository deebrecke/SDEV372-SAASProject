window.onload = async function () {
    await fetchBoardGames();
    await fetchVideoGames();
    let addBoardGameButton = document.querySelector("button#add-bg");
    addBoardGameButton.onclick = addNewBoardGame;
    let addVideoGameButton = document.querySelector("button#add-vg");
    addVideoGameButton.onclick = addNewVideoGame;
    let editVideoGameButton = document.querySelector("button#edit-vg");
    editVideoGameButton.onclick = editVideoGame;
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
    let editButton = document.createElement("td");
    let deleteButton = document.createElement("td")

    //connect them (parent to child)
    body.appendChild(row);
    row.appendChild(tableId)
    row.appendChild(tableName)
    row.appendChild(tableCategory)
    row.appendChild(tableMin)
    row.appendChild(tableMax)
    row.appendChild(editButton)
    row.appendChild(deleteButton)

    //add text or HTML attributes
    tableId.textContent = boardgame.id;
    tableName.textContent = boardgame.name;
    tableCategory.textContent = boardgame.category;
    tableMin.textContent = boardgame.minPlayers;
    tableMax.textContent = boardgame.maxPlayers;
    editButton.textContent = "edit"
    deleteButton.textContent = "delete"
}

function addBoardGamesToTable(boardGameArray) {
    for (let i = 0; i < boardGameArray.length; i++) {
        let boardgame = boardGameArray[i];
        addSingleBoardGameToTable(boardgame);
    }
}

function addSingleVideoGameToTable(videogame) {
    //create HTML elements
    let body = document.querySelector("#vg-tbody");
    let row = document.createElement("tr");
    let tableId = document.createElement("td");
    let tableName = document.createElement("td");
    let tableConsole = document.createElement("td");
    let tableMulti = document.createElement("td");
    //let editButton = document.createElement("td");
    let deleteButton = document.createElement("td")
    let deleteLink =document.createElement("a")

    //connect them (parent to child)
    body.appendChild(row);
    row.appendChild(tableId)
    row.appendChild(tableName)
    row.appendChild(tableConsole)
    row.appendChild(tableMulti)
    //row.appendChild(editButton)
    row.appendChild(deleteButton)
    deleteButton.appendChild(deleteLink)

    //add text or HTML attributes
    tableId.textContent = videogame.id;
    tableName.textContent = videogame.name;
    tableConsole.textContent = videogame.consoleType;
    tableMulti.textContent = videogame.multiplayer;
    //editButton.textContent = "edit"
    deleteButton.textContent = "delete"
    deleteLink.href = "home.html"
}

function addVideoGamesToTable(videoGameArray) {
    for (let i = 0; i < videoGameArray.length; i++) {
        let videogame = videoGameArray[i];
        addSingleVideoGameToTable(videogame);
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

async function editVideoGame(event){
    event.preventDefault();

        let editedVideoGame = {
            id: document.querySelector("input#edit-vg-id"),
            name: document.querySelector("input#vg-name").value,
            consoleType: document.querySelector("input#vg-console").value,
            multiplayer: document.querySelector("input#vg-multi").value
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

        //addSingleVideoGameToTable(jsonObjectReturned);//NO, this adds a new blank line to the table
        //figure out how to change the value here
        //something needs to be done with jsonObjectReturned, but I can't figure out what

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
