<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
<script src="http://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
<h3>Users search</h3>
<hr>

<div>
    <input type="text" id="userName" value="">
	<span>
	  <button id="button-id" type="button">Search</button>
	</span>
</div>

    <script>

        $(function() {
            $("#userName").autocomplete(
                    {
                        source : function(request, response) {
                            $.ajax({
                                url : "/users/searchE",
                                data : {
                                    term : request.term
                                },
                                success : function(data) {
                                    response(data, function(item) {
                                        return {
                                            label : item.name,
                                            value : item.id
                                        }
                                    });
                                }
                            });
                        },
                        minLength : 1
                    });
        });
</script>