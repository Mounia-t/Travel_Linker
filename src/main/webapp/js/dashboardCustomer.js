const sideMenu = document.querySelector('aside');
const menuBtn = document.getElementById('menu-btn');
const closeBtn = document.getElementById('close-btn');
menuBtn.addEventListener('click', () => {
    sideMenu.style.display = 'block';
});

closeBtn.addEventListener('click', () => {
    sideMenu.style.display = 'none';
});
/*---------------------------------image test----- */
function displaySelectedImage(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            
            reader.onload = function (e) {
                document.getElementById("profileImageDisplay").src = e.target.result;
            }
            
            reader.readAsDataURL(input.files[0]);
        }
    }

/*----------------------Zone active pour sidebar-------------------------*/

// Sélectionnez tous les éléments <a> dans la barre latérale
const linkElements = document.querySelectorAll('.sidebar a');

// Ajoutez un gestionnaire d'événements de clic à chaque lien
linkElements.forEach((linkElement) => {
    linkElement.addEventListener('click', function (e) {
        // Supprimez la classe 'active' de tous les liens
        linkElements.forEach((link) => {
            link.classList.remove('active');
        });

        // Ajoutez la classe 'active' au lien cliqué
        this.classList.add('active');
    });
});

// Recherchez et appliquez l'état actif initial
const activeLink = document.querySelector('.sidebar a.active');
if (activeLink) {
    activeLink.classList.add('active');
}
/*------------------------Zone affichage main----------------------------*/




/*------------------------Zone affichage main----------------------------*/
document.addEventListener('DOMContentLoaded', function () {
    // Récupération des éléments de navigation
    const infosBtn = document.getElementById('infosBtn');
    const historyBtn = document.getElementById('historyBtn');
    const favBtn = document.getElementById('favBtn');
    const messagesBtn = document.getElementById('messagesBtn');

    // Récupération des éléments de contenu
    const mainMessages = document.getElementById('mainMessages');
    const mainInfos = document.getElementById('mainInfos');
    const mainHistory = document.getElementById('mainHistory');
    const mainFav = document.getElementById('mainFav');

    // Fonction pour masquer tous les contenus
    function hideMainContent() {
        const contenus = [mainMessages, mainInfos, mainHistory, mainFav];
        contenus.forEach(contenu => {
            contenu.style.display = 'none';
        });
    }

    // Fonction pour afficher un contenu spécifique
    function showMainContent(cible) {
        hideMainContent();
        cible.style.display = 'block';
        cible.classList.add('fade-in-bottom'); // Ajout de la classe pour l'animation fade-in
    }

    // Par défaut, afficher le contenu "Dashboard"
    showMainContent(mainInfos);

    // Gestion des événements de clic
    infosBtn.addEventListener('click', () => {
        showMainContent(mainInfos);
    });

    favBtn.addEventListener('click', () => {
        showMainContent(mainFav);
    });

    historyBtn.addEventListener('click', () => {
        showMainContent(mainHistory);
    });

    messagesBtn.addEventListener('click', () => {
        showMainContent(mainMessages);
    });
});

/*----------------------Zone radioButton messages------------------------*/

document.addEventListener('DOMContentLoaded', function () {
    const allMsg = document.getElementById('allMsg');
    const readMsg = document.getElementById('readMsg');
    const unreadMsg = document.getElementById('unreadMsg');
    const sentMsg = document.getElementById('sentMsg');
    const writeMsg = document.getElementById('writeMsg');

    const allMsgSection = document.getElementById('allMsgSection');
    const readMsgSection = document.getElementById('readMsgSection');
    const unreadMsgSection = document.getElementById('unreadMsgSection');
    const sentMsgSection = document.getElementById('sentMsgSection');
    const writeMsgSection = document.getElementById('writeMsgSection');

    // Fonction pour masquer tous les sections
    function hideAllSections() {
        const sections = [allMsgSection, readMsgSection, unreadMsgSection, sentMsgSection, writeMsgSection];
        sections.forEach(section => {
            section.style.display = 'none';
        });
    }

    // Fonction pour afficher un formulaire spécifique
    function showSection(section) {
        hideAllSections();
        section.style.display = 'block';
        section.classList.add('fade-in-bottom'); // Ajout de la classe pour l'animation fade-in
    }

    // Par défaut, masquer tous les formulaires
    hideAllSections();

    // Fonction d'affichage par défaut de AllMsg
    function showSectionActive() {
        showSection(allMsgSection);
        allMsg.checked = true;
    }
    showSectionActive();

    // Gestion des événements de clic sur les radios
    allMsg.addEventListener('click', () => {
        showSection(allMsgSection);
    });
    readMsg.addEventListener('click', () => {
        showSection(readMsgSection);
    });
    unreadMsg.addEventListener('click', () => {
        showSection(unreadMsgSection);
    });
    sentMsg.addEventListener('click', () => {
        showSection(sentMsgSection);
    });
    writeMsg.addEventListener('click', () => {
        showSection(writeMsgSection);
    });
});

/*----------------------Zone radioButton fav------------------------*/

document.addEventListener('DOMContentLoaded', function () {
    const listFavTravel = document.getElementById('listFavTravel');
    const listFavTravelPlanner = document.getElementById('listFavTravelPlanner');

    const ListFavTravelSection = document.getElementById('ListFavTravelSection');
    const listFavTPSection = document.getElementById('listFavTPSection');

    // Fonction pour masquer tous les sections
    function hideAllSections() {
        const sections = [ListFavTravelSection, listFavTPSection];
        sections.forEach(section => {
            section.style.display = 'none';
        });
    }

    // Fonction pour afficher un formulaire spécifique
    function showSection(section) {
        hideAllSections();
        if (ListFavTravelSection) {
            section.style.display = 'flex';
        } else if (listFavTPSection) {
            section.style.display = 'block';
        }
        section.classList.add('fade-in-bottom'); // Ajout de la classe pour l'animation fade-in
    }

    // Par défaut, masquer tous les formulaires
    hideAllSections();

    // Fonction d'affichage par défaut de AllMsg
    function showSectionActive() {
        showSection(ListFavTravelSection);
        listFavTravel.checked = true;
    }
    showSectionActive();

    // Gestion des événements de clic sur les radios
    listFavTravel.addEventListener('click', () => {
        showSection(ListFavTravelSection);
    });

    listFavTravelPlanner.addEventListener('click', () => {
        showSection(listFavTPSection);
    });
});
/*--------------------------------Zone de suppression des tr du tableau---------------------------------*/

document.addEventListener('DOMContentLoaded', function () {
    const deleteAllMsgBtn = document.getElementById('deleteAllMsg');
    const deleteSelectedAllMsgBtn = document.getElementById('deleteSelectedAllMsg');
    const deleteCheckboxesAllMsg = document.querySelectorAll('.deleteCheckboxAllMsg');

    const deleteReadMsgBtn = document.getElementById('deleteReadMsg');
    const deleteSelectedReadMsgBtn = document.getElementById('deleteSelectedReadMsg');
    const deleteCheckboxesReadMsg = document.querySelectorAll('.deleteCheckboxReadMsg');

    const deleteUnreadMsgBtn = document.getElementById('deleteUnreadMsg');
    const deleteSelectedUnreadMsgBtn = document.getElementById('deleteSelectedUnreadMsg');
    const deleteCheckboxesUnreadMsg = document.querySelectorAll('.deleteCheckboxUnreadMsg');

    const deleteSentMsgBtn = document.getElementById('deleteSentMsg');
    const deleteSelectedSentMsgBtn = document.getElementById('deleteSelectedSentMsg');
    const deleteCheckboxesSentMsg = document.querySelectorAll('.deleteCheckboxSentMsg');

    const deleteListMsgBtn = document.getElementById('deleteListMsg');
    const deleteSelectedListMsg = document.getElementById('deleteSelectedListMsg');
    const deleteCheckboxModifMsg = document.querySelectorAll('.deleteCheckboxModifMsg');

    function updateSelectAllButtonState(button, checkboxes) {
        const allChecked = Array.from(checkboxes).every(checkbox => checkbox.checked);

        if (allChecked) {
            button.textContent = "Tout désélectionner";
        } else {
            button.textContent = "Tout sélectionner";
        }

        // Vérifie si au moins une checkbox est cochée
        const atLeastOneChecked = Array.from(checkboxes).some(checkbox => checkbox.checked);

        if (!atLeastOneChecked) {
            button.textContent = "Tout sélectionner";
        }
    }

    deleteAllMsgBtn.addEventListener('click', () => {
        // Supprime toutes les lignes avec des cases à cocher cochées
        deleteCheckboxesAllMsg.forEach(checkbox => {
            if (checkbox.checked) {
                checkbox.closest('tr').remove();
            }
        });
    });

    deleteSelectedAllMsgBtn.addEventListener('click', () => {
        // Inverser l'état des checkboxes
        deleteCheckboxesAllMsg.forEach(checkbox => {
            checkbox.checked = !checkbox.checked;
        });
        updateSelectAllButtonState(deleteSelectedAllMsgBtn, deleteCheckboxesAllMsg);
    });


    deleteReadMsgBtn.addEventListener('click', () => {
        // Supprimer toutes les lignes avec des cases à cocher cochées
        deleteCheckboxesReadMsg.forEach(checkbox => {
            if (checkbox.checked) {
                checkbox.closest('tr').remove();
            }
        });
    });

    deleteSelectedReadMsgBtn.addEventListener('click', () => {
        // Inverser l'état des checkboxes
        deleteCheckboxesReadMsg.forEach(checkbox => {
            checkbox.checked = !checkbox.checked;
        });

        updateSelectAllButtonState(deleteSelectedReadMsgBtn, deleteCheckboxesReadMsg);
    });

    deleteUnreadMsgBtn.addEventListener('click', () => {
        // Supprimer toutes les lignes avec des cases à cocher cochées
        deleteCheckboxesUnreadMsg.forEach(checkbox => {
            if (checkbox.checked) {
                checkbox.closest('tr').remove();
            }
        });
    });

    deleteSelectedUnreadMsgBtn.addEventListener('click', () => {
        // Inverser l'état des checkboxes
        deleteCheckboxesUnreadMsg.forEach(checkbox => {
            checkbox.checked = !checkbox.checked;
        });

        updateSelectAllButtonState(deleteSelectedUnreadMsgBtn, deleteCheckboxesUnreadMsg);
    });

    deleteSentMsgBtn.addEventListener('click', () => {
        // Supprimer toutes les lignes avec des cases à cocher cochées
        deleteCheckboxesSentMsg.forEach(checkbox => {
            if (checkbox.checked) {
                checkbox.closest('tr').remove();
            }
        });
    });

    deleteSelectedSentMsgBtn.addEventListener('click', () => {
        // Inverser l'état des checkboxes
        deleteCheckboxesSentMsg.forEach(checkbox => {
            checkbox.checked = !checkbox.checked;
        });

        updateSelectAllButtonState(deleteSelectedSentMsgBtn, deleteCheckboxesSentMsg);
    });

    deleteListMsgBtn.addEventListener('click', () => {
        // Supprime toutes les lignes avec des cases à cocher cochées
        deleteCheckboxModifMsg.forEach(checkbox => {
            if (checkbox.checked) {
                checkbox.closest('tr').remove();
            }
        });
    });

    deleteSelectedListMsg.addEventListener('click', () => {
        // Inverser l'état des checkboxes
        deleteCheckboxModifMsg.forEach(checkbox => {
            checkbox.checked = !checkbox.checked;
        });
        updateSelectAllButtonState(deleteSelectedListMsg, deleteCheckboxModifMsg);
    });
});



/*--------------------------------Zone de suppression des tr du tableau utilisateurs---------------------------------*/

document.addEventListener('DOMContentLoaded', function () {
    const deleteCustomer = document.getElementById('deleteCustomer');
    const deleteSelectedCustomer = document.getElementById('deleteSelectedCustomer');
    const deleteCheckboxCustomer = document.querySelectorAll('.deleteCheckboxCustomer');

    const deleteTP = document.getElementById('deleteTP');
    const deleteSelectedTP = document.getElementById('deleteSelectedTP');
    const deleteCheckboxTP = document.querySelectorAll('.deleteCheckboxTP');

    const deletePartner = document.getElementById('deletePartner');
    const deleteSelectedPartner = document.getElementById('deleteSelectedPartner');
    const deleteCheckboxPartner = document.querySelectorAll('.deleteCheckboxPartner');

    function updateSelectAllButtonState(button, checkboxes) {
        const allChecked = Array.from(checkboxes).every(checkbox => checkbox.checked);

        if (allChecked) {
            button.textContent = "Tout désélectionner";
        } else {
            button.textContent = "Tout sélectionner";
        }

        // Vérifie si au moins une checkbox est cochée
        const atLeastOneChecked = Array.from(checkboxes).some(checkbox => checkbox.checked);

        if (!atLeastOneChecked) {
            button.textContent = "Tout sélectionner";
        }
    }

    deleteCustomer.addEventListener('click', () => {
        // Supprime toutes les lignes avec des cases à cocher cochées
        deleteCheckboxCustomer.forEach(checkbox => {
            if (checkbox.checked) {
                checkbox.closest('tr').remove();
            }
        });
    });

    deleteSelectedCustomer.addEventListener('click', () => {
        // Inverser l'état des checkboxes
        deleteCheckboxCustomer.forEach(checkbox => {
            checkbox.checked = !checkbox.checked;
        });
        updateSelectAllButtonState(deleteSelectedCustomer, deleteCheckboxCustomer);
    });


    deleteTP.addEventListener('click', () => {
        // Supprimer toutes les lignes avec des cases à cocher cochées
        deleteCheckboxTP.forEach(checkbox => {
            if (checkbox.checked) {
                checkbox.closest('tr').remove();
            }
        });
    });

    deleteSelectedTP.addEventListener('click', () => {
        // Inverser l'état des checkboxes
        deleteCheckboxTP.forEach(checkbox => {
            checkbox.checked = !checkbox.checked;
        });

        updateSelectAllButtonState(deleteSelectedTP, deleteCheckboxTP);
    });

    deletePartner.addEventListener('click', () => {
        // Supprimer toutes les lignes avec des cases à cocher cochées
        deleteCheckboxPartner.forEach(checkbox => {
            if (checkbox.checked) {
                checkbox.closest('tr').remove();
            }
        });
    });

    deleteSelectedPartner.addEventListener('click', () => {
        // Inverser l'état des checkboxes
        deleteCheckboxPartner.forEach(checkbox => {
            checkbox.checked = !checkbox.checked;
        });

        updateSelectAllButtonState(deleteSelectedPartner, deleteCheckboxPartner);
    });
});


/*--------------------------------Zone de Lecture des tr du tableau avec popupContainer---------------------------------*/


document.addEventListener('DOMContentLoaded', function () {
    const readButtons = document.querySelectorAll('.readBtn');
    const popupContainer = document.getElementById('popupContainer');
    const popupCloseButton = document.getElementById('popupCloseButton');
    const popupContent = document.querySelector('.popup-content');

    readButtons.forEach(button => {
        button.addEventListener('click', () => {
            popupContainer.style.display = 'flex';
            popupContent.classList.add('fade-in-bottom');
        });
    });

    popupCloseButton.addEventListener('click', () => {
        popupContainer.style.display = 'none';
    });
});

/*--------------------------------Zone de réponse des tr du tableau avec popupAnswerContainer---------------------------------*/


document.addEventListener('DOMContentLoaded', function () {

    const popupAnswerButton = document.getElementById('popupAnswerButton');
    const popupAnswerContainer = document.getElementById('popupAnswerContainer');
    const popupCloseAnswerButton = document.getElementById('popupCloseAnswerButton');
    const popupContainer = document.getElementById('popupContainer');
    const popupAnswerContent = document.querySelector('.popup-answer-content');

    // Fonction pour afficher la div avec l'id "popupAnswerContainer"
    function showPopupAnswerContainer() {
        popupAnswerContainer.style.display = 'flex';
        popupAnswerContent.classList.add('fade-in-bottom');
    }

    // Gestionnaire d'événements pour le bouton "Répondre"
    popupAnswerButton.addEventListener('click', showPopupAnswerContainer);

    popupCloseAnswerButton.addEventListener('click', () => {
        popupAnswerContainer.style.display = 'none';
        popupContainer.style.display = 'none';
    });
});
















