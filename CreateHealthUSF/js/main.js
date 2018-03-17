(function ($) {

	$(window).scroll(function(){
		if ($(this).scrollTop() > 100) {
			$('.scrollup').fadeIn();
			} else {
				$('.scrollup').fadeOut();
			}
		});
		$('.scrollup').click(function(){
			$("html, body").animate({ scrollTop: 0 }, 1000);
				return false;
		});
	
	// local scroll
	jQuery('.navbar').localScroll({hash:true, offset: {top: 0},duration: 800, easing:'easeInOutExpo'});

	
	// portfolio
    if($('.isotopeWrapper').length){

        var $container = $('.isotopeWrapper');
        var $resize = $('.isotopeWrapper').attr('id');
        // initialize isotope
        
        $container.isotope({
            itemSelector: '.isotopeItem',
            resizable: false, // disable normal resizing
            masonry: {
                columnWidth: $container.width() / $resize
            }


            
        });

        $('#filter a').click(function(){



            $('#filter a').removeClass('current');
            $(this).addClass('current');
            var selector = $(this).attr('data-filter');
            $container.isotope({
                filter: selector,
                animationOptions: {
                    duration: 1000,
                    easing: 'easeOutQuart',
                    queue: false
                }
            });
            return false;
        });
        
        
        $(window).smartresize(function(){
            $container.isotope({
                // update columnWidth to a percentage of container width
                masonry: {
                    columnWidth: $container.width() / $resize
                }
            });
        });
        

}  


	// fancybox
	jQuery(".fancybox").fancybox();


	if (Modernizr.mq("screen and (max-width:1024px)")) {
			jQuery("body").toggleClass("body");
			
	} else {
		var s = skrollr.init({
			mobileDeceleration: 1,
			edgeStrategy: 'set',
			forceHeight: true,
			smoothScrolling: true,
			smoothScrollingDuration: 300,
				easing: {
					WTF: Math.random,
					inverted: function(p) {
						return 1-p;
					}
				}
			});	
	}



	//scroll menu
	jQuery('.appear').appear();
	jQuery(".appear").on("appear", function(data) {
			var id = $(this).attr("id");
			jQuery('.nav li').removeClass('active');
			jQuery(".nav a[href='#" + id + "']").parent().addClass("active");					
		});


		//parallax
        var isMobile = false;

        if(Modernizr.mq('only all and (max-width: 1024px)') ) {
            isMobile = true;
        }

        
        if (isMobile == false && ($('#parallax1').length  ||isMobile == false &&  $('#parallax2').length ||isMobile == false &&  $('#testimonials').length))
        {


            $(window).stellar({
                responsive:true,
                scrollProperty: 'scroll',
                parallaxElements: false,
                horizontalScrolling: false,
                horizontalOffset: 0,
                verticalOffset: 0
            });

        }
	
	//nicescroll
	$("html").niceScroll({zindex:999,cursorborder:"",cursorborderradius:"2px",cursorcolor:"#191919",cursoropacitymin:.5});
	function initNice() {
		if($(window).innerWidth() <= 960) {
			$('html').niceScroll().remove();
		} else {
			$("html").niceScroll({zindex:999,cursorborder:"",cursorborderradius:"2px",cursorcolor:"#191919",cursoropacitymin:.5});
		}
	}
	$(window).load(initNice);
	$(window).resize(initNice);
	
	jQuery(".summary_view_button").click(function(){
	
		// Get the class of the clicked element
		var myClass = $(this).attr("class");
		
		//Split the class names by SPACE
		var classes = myClass.split(/\s+/);
		
		// Get the device id
		var deviceId = classes[0].split("-")[1];
		
		// Get the unit
		var unit = classes[1].split("-")[1];
		
		// Get the alais name
		var alias = classes[2].split("-")[1];
		//$( "#dialog-message" ).attr("title", "Summary Of: "+alias);
		
		// Make AJAX call to the summary service
		$.ajax({
			type: "POST",
			url: "summary_service.php",
			data: "device_id="+deviceId+"&unit="+unit,
			contentType: 'application/x-www-form-urlencoded',
			dataType: 'text',
			success: function(data, textStatus, jQxhr){
				if(data != -1){
					$( "#dialog-message" ).html(data);
					$( "#dialog-message" ).dialog("open");
					//$( "#dialog-message" ).dialog('open', 'title', 'My New title');
				}
			},
			error: function( jqXhr, textStatus, errorThrown ){
				console.log( errorThrown );
			}
		});
		//$( "#dialog-message" ).dialog("open");
	});

})(jQuery);