<script>
    $(document).ready(function() {
        $('div.list-group').find('a[href="' + location.pathname + '"]')
                .closest('a').addClass('list-group-item active');
    });
</script>

<a href="/users" class="text-center list-group-item">Users</a>
<a href="/pages" class="text-center list-group-item">Pages</a>