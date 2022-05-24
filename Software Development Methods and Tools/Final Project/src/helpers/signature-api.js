var signatureList;
var signature;

/** Documented by Phillip
 * getSignatures will grab the signature text file and return that string back to the caller for storing
 *  @param {string} endpoint - The endpoint for the url to find the signature file
 */
 function getSignatures (endpoint) {
  var sigList;
  console.log(endpoint);
    return $.ajax({
        url: "https://localhost:3000/" + endpoint,
        type: "GET",
        success: function(result){
          signatureList = result
        }

      }); 
  }

/** Documented by Phillip 
 *getRadioID will look for the selected radio button on the taskpane and return the signature index in the master signature array for that radio button */
function getRadioID(){
  var sigID = -1;
  var radioButtons = document.getElementsByName('signature-radio');
  var radioLabels = document.getElementsByName('signature-labels');
  var i = 0;
  while (i < radioButtons.length) {
    if(radioButtons[i].checked)
    {
      sigID = i;
      signature = radioLabels[i].textContent.toString();
    }
    i++;
  }

  i = 0;
  console.log(signature);
  while (i < signatureList.length) {
    if (signatureList[i] == signature)
    {
      console.log(radioLabels[sigID].innerHTML.toString());
      console.log(signatureList[i]);
      sigID = i;
      break;
    }
    i++;
  }
  return sigID;

}

 
/** Documented by Phillip & Sean
 * Takes in html tag and signature list to populate the task pane with signatures
 *  @constructor
 *  @param {string} parent - Parent is a string that correlated to the id tag for the html div that will be populated with the signatures
 *  @param {String[]} sigList - Signature array containing all the signatures from the text file for population on the taskpane
 */
function buildSignatureList(parent, sigList, account) {
  console.log(account)

  // Empty out the HTML container in case it has residue html from old login
  var taskpaneElements = document.querySelectorAll(".signature-html");
  console.log(taskpaneElements);
  for(var i = 0; i < taskpaneElements.length; i++)
  {
    taskpaneElements[i].remove();
  }

  var sigID = 0
  sigList.forEach(function(sig) {
    var key = account + "-";
    console.log(key);
    //console.log(sig.substr(0,key.length - 1));

    /** Documented by Sean
     * @var {SigList} - signature list UI elements
     * @var {radioItem} - radio dial buttons for UI to select functions 
     * @var {desc} - labels for UI elements
     * next line by Weston */
    if(sig.substr(0,key.length) == key)
    {
      var SigList = $('<p/>').addClass('signature-html').appendTo(parent);
  
      var radioItem = $('<input>').addClass('ms-ListItem').addClass('signature-html').addClass('signature-radio').addClass('is-selectable').val(sigID).attr('onclick', "onSignatureSelected()")
        .attr('type', 'radio').attr('name', 'signature-radio').attr('tabindex', 0).attr('id', 'radioButton').appendTo(SigList);
        console.log(radioItem.val());

      var desc = $('<span/>')
        .addClass('text-dark').addClass('rounded').addClass('signature-html').addClass('signature-label').addClass('signature-padding').addClass('is-selectable').attr('name', 'signature-labels')
        .text(sig)
        .appendTo(SigList);
    }
    sigID = sigID + 1;}
  );
}
/** Documented by Sean
 *@constuctor 
 *@param {string} parent - Parent is a string that correlated to the id tag for the html div that will be populated with the signatures
 */
function clearSignatureList(parent){
  $(parent).empty();
}

module.exports = {
  getSignatures: getSignatures,
  getSelectedSignature, getSelectedSignature,
  buildSignatureList: buildSignatureList,
  clearSignatureList: clearSignatureList
}
