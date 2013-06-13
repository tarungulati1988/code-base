<?php
/**
*
*  @author Tarun Gulati, tarun.gulati1988@gmail.com
*  Project: Wanderers
*	LocationForm.php
*
*/
	echo('<div style = "width:100%; height:100%; border-width:1px;
		  border-style:solid; border-color:LIGHTGREY;"><select>');

$file = '../data/GEODATASOURCE-COUNTRY.txt';

$handle = @fopen($file, 'r');
//echo($handle);
if ($handle) {
   while (!feof($handle)) {
   		//echo($handle);
       $line = fgets($handle);
       $item = explode(' ', $line);
       echo '<option value="' . $item[0] . '">' . $item[0] . '</option>' . "\n";
   }
   fclose($handle);
}
echo('</select></div>');
?>