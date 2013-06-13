<?php
/**
*
*  @author Tarun Gulati, tarun.gulati1988@gmail.com
*  Project: Wanderers
*  Wrapper for creating buttons on the fly
*
*/

class Button{
	
	private $varName;

	function showButton($varName){

		$this->varName = $varName;
		
		/**
		* To create a submit button
		*/
		if($varName == "submit" || $varName == "Submit" || $varName == "save" || $varName == "Save" ){
			return ('<input type = "submit" name = "' . $varName . '" id = "' . $varName . '" value = "' . $varName . '">');
		}
		/**
		* To create a reset button
		*/
		else if($varName == "reset" || $varName == "Reset" || $varName == "clear" || $varName == "Clear"){
			return ('<input type = "reset" name = "' . $varName . '" id = "' . $varName . '" value = "' . $varName . '">');
		}
		/**
		* To create a general button
		*/
		else{
			return ('<input type = "button" name = "' . $varName . '" id = "' . $varName . '" value = "' . $varName . '">');
		}
		

	}

}
?>