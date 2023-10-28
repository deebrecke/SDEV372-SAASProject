window.onload = async function(){
    await fetchBoardGames();
    await fetchVideoGames()
}

async function fetchBoardGames()
{
    let uri = "http://localhost:8080/boardgames"
    let config = {
        method: "get"
    };

    let response = await fetch(uri, config);
    let json = await response.json();
    addBoardGamesToTable(json)

}
async function fetchVideoGames()
{
    let uri = "http://localhost:8080/videogames"
    let config = {
        method: "get"
    };

    let response = await fetch(uri, config);
    let json = await response.json();
    addVideoGamesToTable(json)

}


function addSingleBoardGameToTable(boardgame)
{
    //create HTML elements
    let body = document.querySelector("#bg-tbody");
    let row = document.createElement("tr");
    let tableId =document.createElement("td");
    let tableName = document.createElement("td");
    let tableCategory = document.createElement("td");
    let tableMin = document.createElement("td");
    let tableMax = document.createElement("td");

    //connect them (parent to child)
    body.appendChild(row);
    row.appendChild(tableId)
    row.appendChild(tableName)
    row.appendChild(tableCategory)
    row.appendChild(tableMin)
    row.appendChild(tableMax)

    //add text or HTML attributes
    tableId.textContent = boardgame.id;
    tableName.textContent = boardgame.name;
    tableCategory.textContent = boardgame.category;
    tableMin.textContent = boardgame.minPlayers;
    tableMax.textContent = boardgame.maxPlayers;
}

function addBoardGamesToTable(boardGameArray)
{
    for(let i = 0; i < boardGameArray.length; i++)
    {
        let boardgame = boardGameArray[i];
        addSingleBoardGameToTable(boardgame);
    }
}

function addSingleVideoGameToTable(videogame)
{
    //create HTML elements
    let body = document.querySelector("#vg-tbody");
    let row = document.createElement("tr");
    let tableId =document.createElement("td");
    let tableName = document.createElement("td");
    let tableConsole = document.createElement("td");
    let tableMulti = document.createElement("td");

    //connect them (parent to child)
    body.appendChild(row);
    row.appendChild(tableId)
    row.appendChild(tableName)
    row.appendChild(tableConsole)
    row.appendChild(tableMulti)

    //add text or HTML attributes
    tableId.textContent = videogame.id;
    tableName.textContent = videogame.name;
    tableConsole.textContent = videogame.consoleType;
    tableMulti.textContent = videogame.multiplayer;
}

function addVideoGamesToTable(videoGameArray)
{
    for(let i = 0; i < videoGameArray.length; i++)
    {
        let videogame = videoGameArray[i];
        addSingleVideoGameToTable(videogame);
    }
}

//adds a new joke when user enters and presses button
async function addBoardGame(event)
{
    //stop the form from submitting, we will use fetch() instead!
    event.preventDefault();

    let newBoardGame = {
        name: document.querySelector("input#bg-name").value,
        category: document.querySelector("input#bg-category").value,
        minPlayers: document.querySelector("input#bg-min").value,
        maxPlayers: document.querySelector("input#bg-max").value
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
    let json = await response.json();

    addSingleBoardGameToTable(newBoardGame);

    console.log("Game added", json);
}