$("#nav-link-pc").on("click", function(){
    $("#pc").removeClass("notvisible");
    $("#pc").addClass("active");
    $("#smartphone").addClass("notvisible");
    $("#smartphone").removeClass("active");
    $("#photocamera").addClass("notvisible");
    $("#photocamera").removeClass("active");
    $("#TV").addClass("notvisible");
    $("#TV").removeClass("active");
});
$("#nav-link-smartphone").on("click", function(){
    $("#smartphone").removeClass("notvisible");
    $("#smartphone").addClass("active");
    $("#pc").addClass("notvisible");
    $("#pc").removeClass("active");
    $("#photocamera").addClass("notvisible");
    $("#photocamera").removeClass("active");
    $("#TV").addClass("notvisible");
    $("#TV").removeClass("active");
});
$("#nav-link-photocamera").on("click", function(){
    $("#photocamera").removeClass("notvisible");
    $("#photocamera").addClass("active");
    $("#smartphone").addClass("notvisible");
    $("#smartphone").removeClass("active");
    $("#pc").addClass("notvisible");
    $("#pc").removeClass("active");
    $("#TV").addClass("notvisible");
    $("#TV").removeClass("active");
});
$("#nav-link-TV").on("click", function(){
    $("#TV").removeClass("notvisible");
    $("#TV").addClass("active");
    $("#smartphone").addClass("notvisible");
    $("#smartphone").removeClass("active");
    $("#photocamera").addClass("notvisible");
    $("#photocamera").removeClass("active");
    $("#pc").addClass("notvisible");
    $("#pc").removeClass("active");
});