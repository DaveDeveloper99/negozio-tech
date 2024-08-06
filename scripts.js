$("#nav-link-pc").on("click", function(){
    $("#pc").removeClass("notvisible");
    $("#nav-link-pc").addClass("active");
    $("#smartphone").addClass("notvisible");
    $("#nav-link-smartphone").removeClass("active");
    $("#photocamera").addClass("notvisible");
    $("#nav-link-photocamera").removeClass("active");
    $("#TV").addClass("notvisible");
    $("#nav-link-TV").removeClass("active");
});
$("#nav-link-smartphone").on("click", function(){
    $("#smartphone").removeClass("notvisible");
    $("#nav-link-smartphone").addClass("active");
    $("#pc").addClass("notvisible");
    $("#nav-link-pc").removeClass("active");
    $("#photocamera").addClass("notvisible");
    $("#nav-link-photocamera").removeClass("active");
    $("#TV").addClass("notvisible");
    $("#nav-link-TV").removeClass("active");
});
$("#nav-link-photocamera").on("click", function(){
    $("#photocamera").removeClass("notvisible");
    $("#nav-link-photocamera").addClass("active");
    $("#smartphone").addClass("notvisible");
    $("#nav-link-smartphone").removeClass("active");
    $("#pc").addClass("notvisible");
    $("#nav-link-pc").removeClass("active");
    $("#TV").addClass("notvisible");
    $("#nav-link-TV").removeClass("active");
});
$("#nav-link-TV").on("click", function(){
    $("#TV").removeClass("notvisible");
    $("#nav-link-TV").addClass("active");
    $("#smartphone").addClass("notvisible");
    $("#nav-link-smartphone").removeClass("active");
    $("#photocamera").addClass("notvisible");
    $("#nav-link-photocamera").removeClass("active");
    $("#pc").addClass("notvisible");
    $("#nav-link-pc").removeClass("active");
});