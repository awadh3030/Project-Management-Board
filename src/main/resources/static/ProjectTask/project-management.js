document.addEventListener("DOMContentLoaded", function() {
    const createCardBtn = document.getElementById("createCardBtn");
    const cardTitleInput = document.getElementById("cardTitle");
    const cardDescriptionInput = document.getElementById("cardDescription");
    const cardSectionInput = document.getElementById("cardSection");
    const updateForm = document.getElementById("updateForm");
    const updateCardIdInput = document.getElementById("updateCardId");
    const updateTitleInput = document.getElementById("updateTitle");
    const updateDescriptionInput = document.getElementById("updateDescription");
    const updateSectionInput = document.getElementById("updateSection");
    const deleteForm = document.getElementById("deleteForm");
    const deleteCardSelect = document.getElementById("deleteCardSelect");
    const todoContainer = document.getElementById("todoContainer");
    const inProgressContainer = document.getElementById("inProgressContainer");
    const doneContainer = document.getElementById("doneContainer");

    // Base API URL
    const apiUrl = "http://localhost:8080/api";

    // Function to create a new card
    function createCard(title, description, section) {
        const data = {
            title,
            description,
            section: parseInt(section)
        };

        axios.post(`${apiUrl}/boards/2/cards`, data)
            .then(response => {
                const newCard = response.data;
                displayCard(newCard);
            })
            .catch(error => {
                console.error("Error creating card:", error);
            });
    }

    // Function to delete a card
    function deleteCard(cardId) {
        axios.delete(`${apiUrl}/boards/2/cards/${cardId}`)
            .then(() => {
                // Remove the card element from the UI
                const cardElement = document.getElementById(`card_${cardId}`);
                if (cardElement) {
                    cardElement.remove();
                }
            })
            .catch(error => {
                console.error("Error deleting card:", error);
            });
    }

    // Function to update a card
    function updateCard(cardId, title, description, section) {
        const data = {
            title,
            description,
            section: parseInt(section)
        };

        axios.put(`${apiUrl}/boards/2/cards/${cardId}`, data)
            .then(response => {
                const updatedCard = response.data;
                // Update card details in the UI
                const cardElement = document.getElementById(`card_${cardId}`);
                if (cardElement) {
                    cardElement.querySelector("h3").textContent = `Card ID: ${updatedCard.id}`;
                    cardElement.querySelector("h3:nth-child(2)").textContent = `Title: ${updatedCard.title}`;
                    cardElement.querySelector("p:nth-child(3)").textContent = `Description: ${updatedCard.description}`;
                    cardElement.querySelector("p:nth-child(4)").textContent = `Section: ${updatedCard.section === 1 ? 'To Do' : updatedCard.section === 2 ? 'In Progress' : 'Done'}`;
                }
            })
            .catch(error => {
                console.error("Error updating card:", error);
            });
    }

    // Function to display a card in the correct section container
    function displayCard(card) {
        const cardDiv = document.createElement("div");
        cardDiv.classList.add("card");
        cardDiv.setAttribute("id", `card_${card.id}`); // Set a unique ID for each card
        cardDiv.innerHTML = `
            <h3>Card ID: ${card.id}</h3>
            <h3>Title: ${card.title}</h3>
            <p>Description: ${card.description}</p>
            <p>Section: ${card.section === 1 ? 'To Do' : card.section === 2 ? 'In Progress' : 'Done'}</p>
            <button class="deleteCardBtn" data-cardid="${card.id}">Delete</button>
        `;

        // Append the card to the correct section container
        const sectionContainer = card.section === 1 ? todoContainer : card.section === 2 ? inProgressContainer : doneContainer;
        sectionContainer.appendChild(cardDiv);

        // Attach event listener to delete button
        const deleteBtn = cardDiv.querySelector(".deleteCardBtn");
        deleteBtn.addEventListener("click", function() {
            const cardId = deleteBtn.getAttribute("data-cardid");
            deleteCard(cardId);
        });
    }

    // Event listener for creating a card
    createCardBtn.addEventListener("click", function() {
        const title = cardTitleInput.value;
        const description = cardDescriptionInput.value;
        const section = cardSectionInput.value;

        if (title && description && section) {
            createCard(title, description, section);
            cardTitleInput.value = "";
            cardDescriptionInput.value = "";
            cardSectionInput.value = "1"; // Reset section to 'To Do'
        }
    });

    // Event listener for updating a card
    updateForm.addEventListener("submit", function(event) {
        event.preventDefault();
        const cardId = updateCardIdInput.value;
        const title = updateTitleInput.value;
        const description = updateDescriptionInput.value;
        const section = updateSectionInput.value;

        if (cardId && title && description && section) {
            updateCard(cardId, title, description, section);
            updateCardIdInput.value = "";
            updateTitleInput.value = "";
            updateDescriptionInput.value = "";
            updateSectionInput.value = "1"; // Reset section to 'To Do'
        }
    });

    // Function to populate the delete card select options
    function populateDeleteCardSelect(cards) {
        deleteCardSelect.innerHTML = ""; // Clear existing options
        cards.forEach(card => {
            const option = document.createElement("option");
            option.value = card.id;
            option.textContent = `Card ID: ${card.id} - ${card.title}`;
            deleteCardSelect.appendChild(option);
        });
    }

    // Event listener for loading delete card select options
    deleteForm.addEventListener("click", function() {
        axios.get(`${apiUrl}/boards/2/cards`)
            .then(response => {
                const cards = response.data;
                populateDeleteCardSelect(cards);
            })
            .catch(error => {
                console.error("Error loading cards for delete select:", error);
            });
    });

    // Function to handle card deletion
    function handleDeleteCard() {
        const selectedCardId = deleteCardSelect.value;
        if (!selectedCardId) return;

        deleteCard(selectedCardId);
        // Clear delete select options after deletion
        deleteCardSelect.innerHTML = "";
    }

    // Event listener for deleting a card
    deleteForm.addEventListener("submit", function(event) {
        event.preventDefault();
        handleDeleteCard();
    });

    // Function to fetch and display existing cards
    function loadCards() {
        axios.get(`${apiUrl}/boards/2/cards`)
            .then(response => {
                const cards = response.data;
                cards.forEach(displayCard);
            })
            .catch(error => {
                console.error("Error loading cards:", error);
            });
    }

    // Load existing cards on page load
    loadCards();
});
