const sideMenu = document.querySelector('aside');
const menuBtn = document.getElementById('menu-btn');
const closeBtn = document.getElementById('close-btn');

const darkMode = document.querySelector('.dark-mode');

menuBtn.addEventListener('click', () => {
    sideMenu.style.display = 'block';
});

closeBtn.addEventListener('click', () => {
    sideMenu.style.display = 'none';
});

darkMode.addEventListener('click', () => {
    document.body.classList.toggle('dark-mode-variables');
    darkMode.querySelector('span:nth-child(1)').classList.toggle('active');
    darkMode.querySelector('span:nth-child(2)').classList.toggle('active');
})
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

// Récupération des éléments de navigation
const usersBtn = document.getElementById('usersBtn');
const historyBtn = document.getElementById('historyBtn');
const messagesBtn = document.getElementById('messagesBtn');
const addBtn = document.getElementById('addBtn');
const profileBtn = document.getElementById('profileBtn');
const dashboardBtn = document.getElementById('dashboardBtn')
const managedBtn = document.getElementById('managedResaBtn')
const analyticsBtn = document.getElementById('analyticsBtn')

// Récupération des éléments de contenu
const mainMessages = document.getElementById('mainMessages');
const mainUsers = document.getElementById('mainUsers');
const mainHistory = document.getElementById('mainHistory');
const mainAdd = document.getElementById('mainAdd');
const mainProfile = document.getElementById('mainProfile');
const mainDashboard = document.getElementById('mainDashboard')
const mainManagedResa = document.getElementById('mainManagedResa')
const mainAnalytics = document.getElementById('mainAnalytics')
var defaultSection =document.getElementById(document.getElementById('defaultSection').value)

// Fonction pour masquer tous les contenus
function hideMainContent() {
    const contenus = [mainMessages, mainUsers, mainProfile, mainHistory, mainDashboard, mainManagedResa, mainAnalytics];
    contenus.forEach(contenu => {
        contenu.style.display = 'none';
    });
}

// Fonction pour afficher un contenu spécifique
function showMainContent(cible) {
    hideMainContent();
if(cible==null){
	cible=mainDashboard;
}
    cible.style.display = 'block';
    cible.classList.add('fade-in-bottom'); // Ajout de la classe pour l'animation fade-in
}

// Par défaut, afficher le contenu "Dashboard"
showMainContent(defaultSection);
//alert("shownameSection"+document.getElementById('defaultSection').value);
//alert("showSection"+document.getElementById(document.getElementById('defaultSection').value));

// Gestion des événements de clic
usersBtn.addEventListener('click', () => {
    showMainContent(mainUsers);
});

historyBtn.addEventListener('click', () => {
    showMainContent(mainHistory);
});

messagesBtn.addEventListener('click', () => {
    showMainContent(mainMessages);
});

profileBtn.addEventListener('click', () => {
    showMainContent(mainProfile);
});

dashboardBtn.addEventListener('click', () => {
    showMainContent(mainDashboard);
});

managedBtn.addEventListener('click', () => {
    showMainContent(mainManagedResa);
});

analyticsBtn.addEventListener('click', () => {
    showMainContent(mainAnalytics);
});


/*---------------------------------------Zone de glisser déposer ---------------------------------------------*/

function setupDraggableNotifications() {
    const notifications = document.querySelectorAll('.notification');

    let isDragging = false;
    let draggedElement = null;

    notifications.forEach(notification => {
        notification.addEventListener('mousedown', startDragging);
        notification.addEventListener('mousemove', dragElement);
        notification.addEventListener('mouseup', stopDragging);
    });

    function startDragging(event) {
        isDragging = true;
        draggedElement = this;
        this.classList.add('dragging');
    }

    function dragElement(event) {
        if (!isDragging || draggedElement === null) return;
        event.preventDefault();

        const mouseY = event.clientY;

        const prevElement = draggedElement.previousElementSibling;
        const nextElement = draggedElement.nextElementSibling;
        const prevElementRect = prevElement ? prevElement.getBoundingClientRect() : null;
        const nextElementRect = nextElement ? nextElement.getBoundingClientRect() : null;

        if (prevElement && mouseY < prevElementRect.bottom - 10) {
            prevElement.before(draggedElement);
        } else if (nextElement && mouseY > nextElementRect.top + 10) {
            nextElement.after(draggedElement);
        }
    }

    function stopDragging(event) {
        isDragging = false;
        draggedElement.classList.remove('dragging');
        draggedElement = null;
    }
}

function setupNotificationAdd() {
    const rightNotifNumber = document.getElementById('rightNotifNumber');
    const rightContainerNotif = document.getElementById('rightContainerNotif');
    const notificationAdd = document.querySelector('.notification-add');

    function updateNotificationCount() {
        const rightNotifications = document.querySelectorAll('#rightContainerNotif .notification');
        const notificationCount = rightNotifications.length;

        if (notificationCount > 0) {
            rightNotifNumber.style.display = 'flex';
            rightNotifNumber.querySelector('h4').textContent = notificationCount;
        } else {
            rightNotifNumber.style.display = 'none';
        }
    }

    updateNotificationCount(); // Mettre à jour le nombre initial

    // Observer les mutations dans le corps du document
    const observer = new MutationObserver(updateNotificationCount);
    observer.observe(document.body, {
        childList: true,
        subtree: true
    });

    notificationAdd.addEventListener('click', () => {
        const newNotification = document.createElement('div');
        newNotification.classList.add('notification');

        const newNotificationContent = `
            <div class="icon">
                <span class="material-icons-sharp">
                    priority_high
                </span>
            </div>
            <div class="content">
                <div class="info">
                    <h3>Tâche à faire</h3>
                        <textarea class="notification-content" style="font-family: Poppins; font-size: 10.64px; color: #363949; width: 180px;" placeholder="Contenu..."></textarea>
                </div>
                <span class="material-icons-sharp closeCrossNotes">
                    close
                </span>
            </div>
        `;

        newNotification.innerHTML = newNotificationContent;
        rightContainerNotif.insertBefore(newNotification, notificationAdd);



        // Appel à la fonction pour configurer le glisser-déposer pour la nouvelle div
        setupDraggableNotifications();
        // Appel à la fonction pour configurer la suppression de la nouvelle div
        setupNotificationDeletion();
    });
}

function setupNotificationDeletion() {
    // Sélectionne tous les éléments avec la classe "closeCrossNotes"
    const closeCrossNotes = document.querySelectorAll('.closeCrossNotes');

    // Parcourt chaque élément avec la classe "closeCrossNotes"
    closeCrossNotes.forEach(closeSpan => {
        // Ajoute un écouteur d'événements "click" à chaque élément
        closeSpan.addEventListener('click', function () {
            // Trouve l'élément parent avec la classe "notification"
            const notification = this.closest('.notification');

            // Supprime l'élément de l'interface utilisateur
            notification.remove();
        });
    });
}

// Appeler les fonctions de configuration
setupDraggableNotifications();
setupNotificationAdd();
setupNotificationDeletion();


/*----------------------Zone radioButton Travel------------------------*/

document.addEventListener('DOMContentLoaded', function () {
    const listTravel = document.getElementById('listTravel');
    const createTravel = document.getElementById('createTravel');

    const listTravelSection = document.getElementById('ListTravelSection');
    const createTravelSection = document.getElementById('createTravelSection');

    // Fonction pour masquer tous les sections
    function hideAllSections() {
        const sections = [listTravelSection, createTravelSection];
        sections.forEach(section => {
            section.style.display = 'none';
        });
    }

    // Fonction pour afficher un formulaire spécifique
    function showSection(section) {
        hideAllSections();
        if (listTravelSection) {
            section.style.display = 'block';
        } else if (createTravelSection) {
            section.style.display = 'block';
        }
        section.classList.add('fade-in-bottom'); // Ajout de la classe pour l'animation fade-in
    }

    // Par défaut, masquer tous les formulaires
    hideAllSections();

    // Fonction d'affichage par défaut de listTravelSection
    function showSectionActive() {
        showSection(listTravelSection);
        listTravel.checked = true; // Cochez la checkbox
    }
    showSectionActive();


    // Gestion des événements de clic sur les radios
    listTravel.addEventListener('click', () => {
        showSection(listTravelSection);
    });

    createTravel.addEventListener('click', () => {
        showSection(createTravelSection);
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


/*----------------------Zone radioButton create table services------------------------*/

document.addEventListener("DOMContentLoaded", function() {
    const restTable = document.getElementById("restTable");
    const accomoTable = document.getElementById("accomoTable");
    const transTable = document.getElementById("transTable");

    const rbRestTable = document.getElementById("rbRestTable");
    const rbAccomoTable = document.getElementById("rbAccomoTable");
    const rbTransTable = document.getElementById("rbTransTable");

    function hideAllTables() {
        restTable.style.display = "none";
        accomoTable.style.display = "none";
        transTable.style.display = "none";
    }

hideAllTables();

    rbRestTable.addEventListener("click", function() {
        hideAllTables();
        restTable.style.display = "block";
    });

    rbAccomoTable.addEventListener("click", function() {
        hideAllTables();
        accomoTable.style.display = "block";
    });

    rbTransTable.addEventListener("click", function() {
        hideAllTables();
        transTable.style.display = "block";
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
});

/*--------------------------------Zone de Lecture des tr du tableau avec popupContainer---------------------------------*/


document.addEventListener('DOMContentLoaded', function () {
    const readButtons = document.querySelectorAll('.readBtn');
    const popupContainer = document.getElementById('popupContainer');
    const popupCloseButton = document.getElementById('popupCloseButton');

    readButtons.forEach(button => {
        button.addEventListener('click', () => {
            popupContainer.style.display = 'flex';
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

    // Fonction pour afficher la div avec l'id "popupAnswerContainer"
    function showPopupAnswerContainer() {
        popupAnswerContainer.style.display = 'flex';
    }

    // Gestionnaire d'événements pour le bouton "Répondre"
    popupAnswerButton.addEventListener('click', showPopupAnswerContainer);

    popupCloseAnswerButton.addEventListener('click', () => {
        popupAnswerContainer.style.display = 'none';
        popupContainer.style.display = 'none';
    });

});