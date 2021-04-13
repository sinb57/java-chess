const $start = document.querySelector("#start-btn");
const $url = "http://localhost:4567/game";

$start.addEventListener("click", startGame);

function startGame(event) {
    const $roomName = document.querySelector("#roomName").value;
    const $whiteUsername = document.querySelector("#white-username").value;
    const $blackUsername = document.querySelector("#black-username").value;

    const room = {
        roomName: $roomName,
        whiteUsername: $whiteUsername,
        blackUsername: $blackUsername
    }

    const option = {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(room)
    }

    fetch($url, option)
        .then(data => {
            if (data.status === 400) {
                exceptionHandling(data.json());
            } else if (!data.ok) {
                throw new Error(data.status);
            }
            return data.json();
        })
        .then(post => {
            location.href = $url + "/" + post.gameId;
        })
        .catch(error => {
            console.log(error);
            alert(error)
        })

    function exceptionHandling(error) {
        error.then(data => {
            console.log(data);
            alert(data.exceptionMessage);
        })
    }
}