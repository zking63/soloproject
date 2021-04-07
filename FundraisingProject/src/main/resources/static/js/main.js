	<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
	<script src="js/main.js"></script>
$(document).ready(function(){
    $('img').hover(function(){
        var src1 = $(this).attr("src");
        $(this).attr("src", $(this).attr("altSrc"));
        $(this).attr("altSrc", src1);
    },
    function(){
        var src1 = $(this).attr("src");
        $(this).attr("src", $(this).attr("altSrc"));
        $(this).attr("altSrc", src1);
    });
});