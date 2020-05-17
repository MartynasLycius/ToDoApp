function confirmTodoDelete(todoId, afterDeleteAction) {
	if(confirm("Record will be deleted. Are you sure?")) {
		$.ajax({
			url: '/todo/' + todoId,
			type: 'DELETE',
			success: function(result) {
				alert('Record deleted Successfully!');
				afterDeleteAction(); 
			},
			error: function(result) {
				alert('Something wrong , server Failed to delete this record!');
			}
		});
	}
}

function pageRefresh(){
	table.setPage(table.getPage());
}

function goHome(){
	window.location.href = '/todo/';
}