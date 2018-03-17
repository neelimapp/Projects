<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
    <head>
		<!-- BASICS -->
        <meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>Vehicles Management System</title>
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
					<h1><a class="navbar-brand" href="index.php" data-0="line-height:90px;" data-300="line-height:50px;"> Vehicles Management System
					</a></h1>
				</div>
				<div class="navbar-collapse collapse">
					<ul class="nav navbar-nav" data-0="margin-top:20px;" data-300="margin-top:5px;">
						<li class="active"><a href="index.php">Home</a></li>
						<li class="active"><a href="#section-devices">Vehicles</a></li>
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
							<h2 class="slogan">Welcome to </br>"Vehicles World"</h2>
							<p>
							Now you can view, search, favoritize and explore vehicles details more SIMPLY !!!..
				
							</p>
							<p><H3> Just CLICK ->>> <a class="text-info" href="#section-devices">Vehicles</a> </H3></p>
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
							<h2 class="section-heading animated" data-animation="bounceInUp">Available Vehicles</h2>
							<p>Please choose your favourite vehicle from the list by checking the check box under favorite column</p>
						</div>
					</div>
				</div>
				
				<div id="dialog-message" title="SUMMARY">
				  
				</div>
				<!-- constructing devices table -->
				<div>
					<?php
					$VEHICLES_ROWS_HTML = "";
										
					if (($handle = fopen("data/vehicles.csv", "r")) !== FALSE) {
						$HEADER_ROW = 1;
						$csv_row = 1;
						while (($data = fgetcsv($handle, 1000, ",")) !== FALSE) {
							if($csv_row > $HEADER_ROW){ // to check if it is header row
								$VEHICLES_ROWS_HTML .= '<tr>';
																
								$num = count($data);
								
								for ($c=1; $c < $num; $c++) {
									if($c == $num-1) {
										if($data[$c] == "TRUE") {
											$VEHICLES_ROWS_HTML .= '<td><input id="toggle-heart'.$csv_row.'" type="checkbox" checked/>
																	<label for="toggle-heart">❤</label><td>';
										} else {
											$VEHICLES_ROWS_HTML .= '<td><input id="toggle-heart'.$csv_row.'" type="checkbox" />
																	<label for="toggle-heart">❤</label><td>';
										}
									} else {
										$VEHICLES_ROWS_HTML .= '<td>'.$data[$c].'</td>';
									}
								}
								$VEHICLES_ROWS_HTML .= '</tr>';
							}
							$csv_row++;							
						}
						fclose($handle);
					}
				  ?>
				</div>

				<div class="table-responsive">
				  
				  <table id = "vehicleTable" class="table table-hover table-inverse">
				  <thead>
					<tr>
					  <th class="hidden">VEHICLE_ID</th>
					  <th><p><input type="text" id="make" onkeyup="search(0, 'make')" placeholder="Make"></p>Make 
					  <button id="sortAscMake" class="button" onclick="sort(0, 'ASC')">s</button>
					  <button id="sortDescMake" class="button" onclick="sort(0, 'DESC')">s</button></th>
					  <th><p><input type="text" id="model" onkeyup="search(1, 'model')" placeholder="Model"></p>Model 
					  <button id="sortAscModel" class="button" onclick="sort(1, 'ASC')">s</button>
					  <button id="sortDescModel" class="button" onclick="sort(1, 'DESC')">s</button></th>
					  <th><p><input type="text" id="year" onkeyup="search(2, 'year')" placeholder="Year"></p>Year 
					  <button id="sortAscYear" class="button" onclick="sort(2,'ASC')">s</button>
					  <button id="sortDescYear" class="button" onclick="sort(2, 'DESC')">s</button></th>
					  <th><p><input type="text" id="package" onkeyup="search(3, 'package')" placeholder="Package"></p>Package 
					  <button id="sortAscPackage" class="button" onclick="sort(3, 'ASC')">s</button>
					  <button id="sortDescPackage" class="button" onclick="sort(3, 'DESC')">s</button></th>
					  <th><p><input type="text" id="fuelType" onkeyup="search(4, 'fuelType')" placeholder="Fuel Type"></p>Fuel Type 
					  <button id="sortAscFuelType" class="button" onclick="sort(4, 'ASC')">s</button>
					  <button id="sortDescFuelType" class="button" onclick="sort(4, 'DESC')">s</button></th>
					  <th><p><input type="text" id="transmission" onkeyup="search(5, 'transmission')" placeholder="Transmission"></p>Transmission 
					  <button id="sortAscTransmission" class="button" onclick="sort(5, 'ASC')">s</button>
					  <button id="sortDescTransmission" class="button" onclick="sort(5, 'DESC')">s</button></th>
					  <th><p></p>Favorite</th>
					</tr>
				  </thead>
				  <tbody>
				  
					<?php echo $VEHICLES_ROWS_HTML ?>
					
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
				<p class="color-white"><b>Vehicles Management System</b></p>
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
					<div class="col-sm-12"><p>Copyright &copy; 2018 Vehicles Management System - by <a href="#">Neelima Parakala</a></p></div>
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
	
	<script>
	// sort the html table based on the column in Ascending or Descending order
	function sort(col, type){
		var tbl = document.getElementById("vehicleTable").tBodies[0];
		var store = [];
		for(var i=0, len=tbl.rows.length; i<len; i++){
			var row = tbl.rows[i];
			var sortVar = row.cells[col].innerHTML;
			if(sortVar) store.push([sortVar, row]);
		}
		store.sort();
		if(type == "DESC") {
			store.reverse();
		}
		for(var i=0, len=store.length; i<len; i++){
			tbl.appendChild(store[i][1]);
		}
		store = null;
	}
	
	function search(col, columnId) {
		// Declare variables 
		var input, filter, table, tr, td, i;
		input = document.getElementById(columnId);
		filter = input.value.toUpperCase();
		table = document.getElementById("vehicleTable");
		tr = table.getElementsByTagName("tr");

		// Loop through all table rows, and hide those who don't match the search query
		for (i = 0; i < tr.length; i++) {
			td = tr[i].getElementsByTagName("td")[col];
			if (td) {
				if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
					tr[i].style.display = "";
				} else {
					tr[i].style.display = "none";
				}
			} 
		}
	}
	
	</script>

	</body>
</html>