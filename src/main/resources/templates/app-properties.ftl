<script>
    var AppProperties = {
        name: 'MonolithBootstrapApplication',
        profiles: '${activeprofiles}'
    };
    var metas = document.getElementsByTagName('meta');

    for (var i=0; i<metas.length; i++) {
        AppProperties[metas[i].getAttribute("name")] =metas[i].getAttribute("content");
    }
    console.log(AppProperties);
</script>