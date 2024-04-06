/**
 * This is the code for the functionality of the web application
 */

 document.addEventListener('DOMContentLoaded', function () {
    var form = document.getElementById('taskForm');
    form.onsubmit = function (e) {
        e.preventDefault();
        var name = document.getElementById('name').value;
        var description = document.getElementById('description').value;
        var dueDate = document.getElementById('dueDate').value;
        fetch('/tasks', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ name: name, description: description, dueDate: dueDate })
        })
        .then(response => response.json())
        .then(task => {
            var tasksContainer = document.getElementById('tasksContainer');
            tasksContainer.innerHTML += renderTask(task);
            form.reset();
        })
        .catch(error => console.error('Error:', error));
    };
});

function toggleTaskComplete(taskId, btn) {
    fetch('/tasks/' + taskId + '/complete', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        }
    })
    .then(response => response.json())
    .then(task => {
        var taskElement = btn.parentNode;
        //taskElement.querySelector('span').textContent = task.isComplete ? 'Done' : 'Not Done';
        btn.textContent = task.isComplete ? 'Mark as Not Done' : 'Mark as Done';
    })
    .catch(error => console.error('Error:', error));
}

function hideTasksByDescription() {
    // Get the input value
    var searchWord = document.getElementById('searchDescription').value.toLowerCase();
    // Get all task divs
    var tasks = document.querySelectorAll('#tasksContainer > div');
    // Iterate through each task
    tasks.forEach(function(task) {
        // Get the description from the task element
        var description = task.querySelector('p:nth-child(2) span').textContent.toLowerCase();
        // Check if the description contains the specified word
        if (description.includes(searchWord)) {
            // Show the task
            task.style.display = 'block';
        } else {
            // Hide the task
            task.style.display = 'none';
        }
    });
}

function hideTasksByDate() {
    // Get the input value
    var searchWord = document.getElementById('searchDate').value.toLowerCase();
    // Get all task divs
    var tasks = document.querySelectorAll('#tasksContainer > div');
    // Iterate through each task
    tasks.forEach(function(task) {
        // Get the description from the task element
        var description = task.querySelector('p:nth-child(3) span').textContent.toLowerCase();
        console.log(description);
        // Check if the description contains the specified word
        if (description.includes(searchWord)) {
            // Show the task
            task.style.display = 'block';
        } else {
            // Hide the task
            task.style.display = 'none';
        }
    });
}

function hideTasksByStatus(status) {
    // Get all task divs
    var tasks = document.querySelectorAll('#tasksContainer > div');
    // Iterate through each task
    tasks.forEach(function(task) {
        // Get the button element in the task
        var button = task.querySelector('button');
        // Check if a button exists
        if (button) {
            // Get the text content of the button
            var buttonText = button.textContent.toLowerCase();

            // Check if the button text matches the specified status
            var isComplete = buttonText.includes(status);

            // Show or hide the task based on the specified status
            task.style.display = isComplete ? 'block' : 'none';
        }
    });
}


function renderTask(task) {
    // Format the date to YYYY-MM-DD
    var dueDate = new Date(task.dueDate).toISOString().split('T')[0];

    return '<div class="task-item" id="task-' + task.id + '">' +
    		//'<span class="option-a">' + 'Not Done' + '</span> ' +
           '<p><span class="task-main">' + 'Task: ' + task.name + '</span></p> ' +
           '<p><span>' + 'Description: ' + task.description + '</span></p>' +
           '<p><span>' + 'Due Date: ' + dueDate + '</span></p> ' +
           //'<span>' + (task.isComplete ? 'Done' : 'Not Done') + '</span> ' +
           '<button onclick="toggleTaskComplete(' + task.id + ', this)" ' +
           'style="background-color: ' + (task.isComplete ? 'red' : 'green') + ';">' +
           (task.isComplete ? 'Mark as Not Done' : 'Mark as Done') + '</button> ' +
           '<button onclick="deleteTask(' + task.id + ', this)">Delete Task</button>' +
           '</div>';
}

function deleteTask(taskId, btn) {
    fetch('/tasks/' + taskId, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
        }
    })
    .then(response => {
        if (response.ok) {
            // Remove the task element from the UI
            var taskElement = document.getElementById('task-' + taskId);
            if (taskElement) {
                taskElement.remove();
            }
        } else {
            console.error('Task could not be deleted.');
        }
    })
    .catch(error => console.error('Error:', error));
}

function refreshPage() {
    // Reload the page
    location.reload();
}