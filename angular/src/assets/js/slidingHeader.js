
(function() {
document.addEventListener("DOMContentLoaded", function(event) {
	var elem = document.querySelector("#header");
	console.log(elem);
    var header = new Headroom(elem, {
        tolerance: 5,
        offset : 205,
        classes: {
          initial: "animated",
          pinned: "slideDown",
          unpinned: "slideUp"
        }
    });
    header.init();
});
}());

/*
(function() {

    var bttHeadroom = new Headroom(document.getElementById("btt"), {
        tolerance : 0,
        offset : 500,
        classes : {
            initial : "slide",
            pinned : "slide--reset",
            unpinned : "slide--down"
        }
    });
    //bttHeadroom.init();
}());
*/

