
/** Documented by Sean
* Represents an account selection function
* @constructor
*/
(function(){
  'use strict';
  var config;
  var signatureList;

  /** Documented by Sean
  *
  *@var {signature} signature - The signature from a text file  
  *Code by Philip */
  Office.onReady($.when(getSignatures("signature")).then(storeSignatures));



  Office.initialize = function(reason){
    jQuery(document).ready(function(){

      config = getConfig();

            /** Documented by Sean
      *
      * Code by Phillip - Functionality for account selection via dropdown menu within taskpane */
      if(getIsAccountSelected() == false){
      console.log("account not selected");
      buildAccountList('#dropdown-menu', signatureList);

      $('#account-selection').toggle(true);
      $('#signature-content').toggle(false);
      }
      else
      {
        console.log("account is selected");
        buildAccountList('#dropdown-menu', signatureList);
        updateAccountSelectionStatus();
      }


      /** Code by Philip - Insert Signature button functionality for taskpane */
      $('#insert-signature').on('click', function(){

        /** Documented by Sean
        * @var {signature} - signature from text file
        * @deprecated
        * Deprecated - var signature = getSelectedSignature(); */
        var signature = signatureList[getRadioID()];
        var selectedSignature = "<br> " + signature + " <br>";
        Office.context.mailbox.item.body.setSelectedDataAsync(selectedSignature, {coercionType: 'html'});

      });

      /** Code by Philip - Log out button for the taskpane */
      $('#log-out').on('click', function(){
        logOut();
      });

      $('#random-signature').on('click', function(){
        /** Documented by Sean 
        *@var {randomNumber} - random index from the signature list
        *@borrows {floor) - from random class to change random number into integer for grabbing indices
        * Code by Sean and Philip */
            var accountSigs = getAccountIndices(getAccount());
            console.log(accountSigs);
            /** Code by Sean - floor method to round to integer for random function to grab index of list or array */
            var randomNumber = Math.floor(Math.random() * (accountSigs.length));
            randomNumber = accountSigs[randomNumber];
            /** Code by Philip to grab the break point tags from HTML formatting */
            var randomSignature = "<br> " + signatureList[randomNumber] + " <br>";
            Office.context.mailbox.item.body.setSelectedDataAsync(randomSignature, {coercionType: 'html'});
      });

      /** Documented by Sean
      *Code by Philip - Save signature button functionality */
      $('#save-signature').on('click', function(){
        /** Documented by Sean
        *@var {newSig} - variable for new signature */
        var newSig = $('#new-signature').val();

        $.ajax({
          url: "https://localhost:3000/set-signature?newSignature=" + getAccount() + "-" + newSig,
        })

        /** Documented by Sean
        *Build signature list functionality */
        buildSignatureList('#signatures-list', signatureList, getAccount());

      })


      /** Documented by Phillip
      *Written by Jose with the help of Weston - Delete signature from list functionality */
      $('#delete-signature').on('click', function(){

/** Documented by Sean
*@deprecated 
- Deprecated code -
        var signID = - 1;
        var deleteSig;
        var radioButtons = document.getElementsByName('signature-radio');
        var i = 0;
        while (i < radioButtons.length){
          if(radioButtons[i].checked){
            signID = i;
            break;
          }
          i++;
      }

*/

      $.ajax({
        url: "https://localhost:3000/delete-signature?deleteSignature=" + getRadioID() /*signID*/,
        type: "GET"
      });
        
        /** Documented by Sean
        *@constructor
        *@param {signatureList} - created list from #signatures-list
        *@param {#signatures-list} - pulled from text file
        *Build signature list functionality */
        buildSignatureList('#signatures-list', signatureList, getAccount());
        
      });
    });
  };


   /**Documented by Phillip 
  *Code by Philip - Functionality to store signature and split by the newline character */
  function storeSignatures(signatures)

  {
    signatureList = signatures.split("\n");
  }

  // Code by Philip with the help of stack overflow from https://stackoverflow.com/questions/14659098/checking-if-a-textbox-is-empty-in-javascript
  function checkTextField(field) {
    $('#save-signature').removeAttr('disabled');

  }
})();
