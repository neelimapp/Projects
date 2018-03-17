<?php
// Initialize an empty array for holding the Summary details
$SUMMARY_DETAILS = array();

// Getting the Sensor 'id' from the POST Request.
// POST request will be made by AJAX on clicking the 'Summary' button
if( $_POST["device_id"] && $_POST["unit"]){
	$SUMMARY_DETAILS["id"] = $_POST["device_id"];
	$SUMMARY_DETAILS["unit"] = $_POST["unit"];
}else{
	// If the Sensor 'id' is not available in the POST request
	// then stop the execution
	die(-1);
}

// This will hold the final HTML of the SUMMARY Details
// that will be sent as response
$SUMMARY_TABLE_HTML = "";

// Check if the File is available first
if (($handle = fopen("data/sensor_summary.csv", "r")) !== FALSE) {
	
	// Initialize the variables for the future use in the code
	$HEADER_ROW = 1;
	$DEVICE_ID_COLUMN_INDEX = 0;
	$DEVICE_TIMESTAMP_COLUMN_INDEX = 1;
	$DEVICE_VALUE_COLUMN_INDEX = 2;
	$csv_row = 1;
	
	// Initialize the same array to hold all the dates
	$SUMMARY_DETAILS["dates"] = array();
	
	$previousDateString = "";
	
	// This array will hold the Array("hour" => "value")
	$hourAndValue = array();
	
	// Boolean to check if date is added first
	$bIsDateAdded = false;
	
	// Loop through the CSV file
	while (($data = fgetcsv($handle, 1000, ",")) !== FALSE) {
		
		// Check and skip if the row is the Header row
		if($csv_row > $HEADER_ROW){
			
			// Check if the sensor id matches the id in the POST Request
			if($data[$DEVICE_ID_COLUMN_INDEX] == $SUMMARY_DETAILS["id"]){
				
				// Get the parsed date from the Timestamp data in the CSV
				$parsedDate = date_parse ( $data[$DEVICE_TIMESTAMP_COLUMN_INDEX] );
								
				// Construct the date string
				$dateString =  $parsedDate["month"]."/".$parsedDate["day"]."/".$parsedDate["year"];
				
				// Get the Hour from the parsed Timestamp
				$hour = $parsedDate["hour"];
				
				$hourAndValue[$hour] = $data[$DEVICE_VALUE_COLUMN_INDEX];
				
				if($previousDateString != "" && $previousDateString != $dateString){
					// Preserve the last value from the array
					// because it is for the next date
					$tmpArray = array_slice($hourAndValue, -1);
					
					// Remove the last element if it is the first date
					if(!$bIsDateAdded){
						unset($hourAndValue[$hour]);
					}
					
					// Put everything into the Main Array					
					$SUMMARY_DETAILS["dates"][$previousDateString] = $hourAndValue;
					
					// Set the date added boolean to TRUE
					$bIsDateAdded = true;
					
					// Reset the array
					$hourAndValue = array();
					
					// Put back the preserved value again
					$hourAndValue = $tmpArray;
				}
				$previousDateString = $dateString;
				
				//print_r($dateString);
			}			
			
			$num = count($data);
			
			//print_r($data);
		}
		$csv_row++;							
	}
	
	// Put the last date and value details into the Main Array					
	$SUMMARY_DETAILS["dates"][$previousDateString] = $hourAndValue;
	
	// Close the CSV file handler
	fclose($handle);
		
	$SUMMARY_TABLE_HTML = '<table class="table table-hover table-inverse">';
	$SUMMARY_TABLE_HTML .= '<thead>';
	$SUMMARY_TABLE_HTML .= '<tr>';
	$SUMMARY_TABLE_HTML .= '<th>Hours / Date</th>';
	
	
	$tempHTMLForUnit = "";
	foreach($SUMMARY_DETAILS["dates"] as $key => $value){	
		$SUMMARY_TABLE_HTML .= '<th>'.$key.'</th>';		
		$tempHTMLForUnit .= '<th>('.$_POST['unit'].')</th>';
	}
	
	$SUMMARY_TABLE_HTML .= '</tr>';
	$SUMMARY_TABLE_HTML .= '<tr>';
	$SUMMARY_TABLE_HTML .= '<th><i></i></th>';
	$SUMMARY_TABLE_HTML .= $tempHTMLForUnit;
	$SUMMARY_TABLE_HTML .= '</tr>';
	$SUMMARY_TABLE_HTML .= '</thead>';
	
	$SUMMARY_TABLE_HTML .= '<tbody>';
	
	/*$SUMMARY_TABLE_HTML .= '<tr>';
	$SUMMARY_TABLE_HTML .= '<td></td>';
	$SUMMARY_TABLE_HTML .= $tempHTMLForUnit;
	$SUMMARY_TABLE_HTML .= '</tr>';*/
		
	// Iterate till 23 Hrs
	for($i = 0; $i <= 23; $i++){
		$SUMMARY_TABLE_HTML .= '<tr>';
		$SUMMARY_TABLE_HTML .= '<th scope="row">'.$i.'</th>';
		foreach($SUMMARY_DETAILS["dates"] as $key => $value){
			if(array_key_exists($i, $value)){
				$SUMMARY_TABLE_HTML .= '<td>'.$value[$i].'</td>';
			}else{
				$SUMMARY_TABLE_HTML .= '<td>-</td>';
			}			
		}
		$SUMMARY_TABLE_HTML .= '</tr>';
	}	
	
	$SUMMARY_TABLE_HTML .= '';
	$SUMMARY_TABLE_HTML .= '</tbody>';
	$SUMMARY_TABLE_HTML .= '</table>';
	
	echo $SUMMARY_TABLE_HTML;
}
?>