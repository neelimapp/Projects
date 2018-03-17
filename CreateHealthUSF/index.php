<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
    <head>
		<!-- BASICS -->
        <meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>CREATE Health, USF</title>
        <meta name="description" content="">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" type="text/css" href="css/isotope.css" media="screen" />
		<link rel="stylesheet" type="text/css" href="js/jqueryui/jquery-ui.css" media="screen" />
		<link rel="stylesheet" type="text/css" href="js/jqueryui/jquery-ui.theme.css" media="screen" />
		
		
		<link rel="stylesheet" type="text/css" href="css/isotope.css" media="screen" />	
		<link rel="stylesheet" href="js/fancybox/jquery.fancybox.css" type="text/css" media="screen" />
		<link rel="stylesheet" href="css/bootstrap.css">
		<link rel="stylesheet" href="css/bootstrap-theme.css">
        <link rel="stylesheet" href="css/style.css">
		<!-- skin -->
		<link rel="stylesheet" href="skin/default.css">
    </head>
	 
    <body>
		<section id="header" class="appear"></section>
		<div class="navbar navbar-fixed-top" role="navigation" data-0="line-height:100px; height:100px; background-color:rgba(0,0,0,0.3);" data-300="line-height:60px; height:60px; background-color:rgba(0,0,0,1);">
			 <div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
						<span class="fa fa-bars color-white"></span>
					</button>
					<h1><a class="navbar-brand" href="index.php" data-0="line-height:90px;" data-300="line-height:50px;">{ Internet Of Things }
					</a></h1>
				</div>
				<div class="navbar-collapse collapse">
					<ul class="nav navbar-nav" data-0="margin-top:20px;" data-300="margin-top:5px;">
						<li class="active"><a href="index.php">Home</a></li>
						<li><a href="#section-devices">Devices</a></li>
					</ul>
				</div><!--/.navbar-collapse -->
			</div>
		</div>

		<section class="featured">
			<div class="container"> 
				<div class="row mar-bot40">
					<div class="col-md-6 col-md-offset-3">
						
						<div class="align-center">
							<i class="fa fa-bolt fa-5x mar-bot20"></i>
							<h2 class="slogan">Welcome to </br>"Your Week in Review"</h2>
							<p>
							Now you can view the summary of activity in your Smart Home from past week more SIMPLY !!!..
				
							</p>
							<p><H3> Just CLICK ->>> <a class="text-info" href="#section-devices">Devices</a> </H3></p>
						</div>
					</div>
				</div>
			</div>
		</section>
		
			
			
		<!-- about -->
		<section id="section-devices" class="section appear clearfix">
		<div class="container">

				<div class="row mar-bot40">
					<div class="col-md-offset-3 col-md-6">
						<div class="section-header">
							<h2 class="section-heading animated" data-animation="bounceInUp">Available Devices</h2>
							<p>Please click on the "Week's Summary" button to see the Activity Summary...</p>
						</div>
					</div>
				</div>
				
				<div id="dialog-message" title="SUMMARY">
				  
				</div>
				<!-- constructing devices table -->
				<div>
					<?php
					$DEVICES_ROWS_HTML = "";
										
					if (($handle = fopen("data/sensor.csv", "r")) !== FALSE) {
						$HEADER_ROW = 1;
						$DEVICE_ALIAS_COLUMN = 1;
						$DEVICE_MANUFACTURER_COLUMN = 2;
						$csv_row = 1;
						$html_table_row_count = 1;
						while (($data = fgetcsv($handle, 1000, ",")) !== FALSE) {
							if($csv_row > $HEADER_ROW){
								$DEVICES_ROWS_HTML .= '<tr>';
								$DEVICES_ROWS_HTML .= '<th scope="row">'.$html_table_row_count.'</th>';
																
								$num = count($data);
								
								for ($c=0; $c < $num; $c++) {
									if($c == $DEVICE_ALIAS_COLUMN || $c == $DEVICE_MANUFACTURER_COLUMN){
										$DEVICES_ROWS_HTML .= '<td>'.$data[$c].'</td>';
									}else{
										$DEVICES_ROWS_HTML .= '<td class=hidden>'.$data[$c].'</td>';
									}
								}
								$DEVICES_ROWS_HTML .= '<td><button type="button" class="device_id-'.$data[0].' unit-'.$data[6].' alias-'.$data[$DEVICE_ALIAS_COLUMN].' summary_view_button btn btn-info">SUMMARY</button></td>';
								$DEVICES_ROWS_HTML .= '</tr>';
								
								$html_table_row_count++;
							}
							$csv_row++;							
						}
						fclose($handle);
					}
				  ?>
				</div>

				<div class="table-responsive">
				  <table class="table table-hover table-inverse">
				  <thead>
					<tr>
					  <th>#</th>
					  <th class="hidden">DEVICE_ID</th>
					  <th>Device Alias</th>
					  <th>Manufacturer</th>
					  <th class="hidden">MODEL</th>
					  <th class="hidden">TYPE</th>
					  <th class="hidden">CLASS</th>
					  <th class="hidden">UNIT</th>
					  <th>(Click to View Summary)</th>
					</tr>
				  </thead>
				  <tbody>
				  
					<?php echo $DEVICES_ROWS_HTML ?>
					
				  </tbody>
				  </table>
				</div>
		</div>
		</section>
		<!-- /about -->
		
		<!-- spacer section:stats -->
		<section id="parallax1" class="section pad-top40 pad-bot40" data-stellar-background-ratio="0.5">
			<div class="container">
            <div class="align-center pad-top40 pad-bot40">
                <blockquote class="bigquote color-white">Thanks For Using</blockquote>
				<p class="color-white"><b>CREATE Health, USF</b></p>
            </div>
			</div>	
		</section>
		
		<!-- section works -->
		
	<section id="footer" class="section footer">
		<div class="container">
			<div class="row animated opacity mar-bot20" data-andown="fadeIn" data-animation="animation">
				<div class="col-sm-12 align-center">
                    <ul class="social-network social-circle">
                        <li><a href="#" class="icoRss" title="Rss"><i class="fa fa-rss"></i></a></li>
                        <li><a href="#" class="icoFacebook" title="Facebook"><i class="fa fa-facebook"></i></a></li>
                        <li><a href="#" class="icoTwitter" title="Twitter"><i class="fa fa-twitter"></i></a></li>
                        <li><a href="#" class="icoGoogle" title="Google +"><i class="fa fa-google-plus"></i></a></li>
                        <li><a href="#" class="icoLinkedin" title="Linkedin"><i class="fa fa-linkedin"></i></a></li>
                    </ul>				
				</div>
			</div>

			<div class="row align-center copyright">
					<div class="col-sm-12"><p>Copyright &copy; 2016 CREATE Health, USF - by <a href="#">Neelima Parakala</a></p></div>
			</div>
		</div>

	</section>
	<a href="#header" class="scrollup"><i class="fa fa-chevron-up"></i></a>	

	<script src="js/modernizr-2.6.2-respond-1.1.0.min.js"></script>
	<script src="js/jquery.js"></script>
	<script src="js/jqueryui/jquery-ui.min.js"></script>	
	<script src="js/jquery.easing.1.3.js"></script>
    <script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.isotope.min.js"></script>
	<script src="js/jquery.nicescroll.min.js"></script>
	<script src="js/fancybox/jquery.fancybox.pack.js"></script>
	<script src="js/skrollr.min.js"></script>		
	<script src="js/jquery.scrollTo-1.4.3.1-min.js"></script>
	<script src="js/jquery.localscroll-1.2.7-min.js"></script>
	<script src="js/stellar.js"></script>
	<script src="js/jquery.appear.js"></script>
	<script src="js/validate.js"></script>
    <script src="js/main.js"></script>
	<!-- initializing the summary Dialogue popup -->
	<script>
		$( function() {
			$( "#dialog-message" ).dialog({
				resizable: false,
				autoOpen: false,
				modal: true,
				width:'auto',
				buttons: {
				Ok: function() {
					$( this ).dialog( "close" );
				}
				}
			});
		});
	</script>
	</body>
</html>