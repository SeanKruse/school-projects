var isAccountSelected = false;
var accountSelected = "";
var signatureList;
var accountList = [];

$.ajax({
  url: "https://localhost:3000/accounts",
  type: "GET",
  success: function(result){
    result.forEach(account => {
      accountList.push(account["username"]);
      if (account["islogged"] == "1"){
        isAccountSelected = true;
        accountSelected = account["username"];
        console.log(account["username"] + "is logged in");
      }
    });
  }
}); 

/** Documented by Sean
 *@constuctor
 *@param {string} parent - Parent is a string that correlated to the id tag for the html div that will be populated with the signatures
 *@param {string} acctList - list of accounts for the account management system
 *@param {string} signaturesForMe - selected per-account signatures from filtering function
 *@var AcctList - UI element for account list
 *@var radioItem - UI element for radio dial
 *@var desc - UI elements for formatting 
 */
function buildAccountList(parent, signaturesForMe) {
    signatureList = signaturesForMe;
    var acctID = 0

    accountList.forEach(function(acct) {
      var AcctList = $('<div>').addClass('form-check').appendTo(parent);

      var radioItem = $('<input>').addClass('form-check-input').attr('onclick', "onAccountSelected()")
        .attr('type', 'radio').attr('name', 'account-item').attr('tabindex', 0).attr('id', acctID.toString()).val(acct).hide().appendTo(AcctList);

      var desc = $('<label/>')
        .addClass('form-check-label').addClass('dropdown-item')
        .text(acct)
        .attr('for', acctID.toString())
        .attr("onclick", "onAccountSelected()")
        .attr('name', 'account-labels')
        .attr('type', 'radio')
        .val(acctID)
        .appendTo(AcctList);

      acctID = acctID + 1;}
    );
  }

 /** Documented by Sean
 *@constuctor
 *@var accountID -
 *@var dropDownItems - variable for the specefic links in the drop-down menu
 * Setting up the selection of account function with drop-down implementation */
function onAccountSelected() {
  var accountID = -1;
  var dropDownItems = document.getElementsByName('account-item');
  var i = 0;
  while (i < dropDownItems.length) {
    if(dropDownItems[i].checked)
    {
      accountID = i;
      break;
    }
    i++;
  }
  if(accountID != -1)
  {
    console.log("account being selected is" + accountList[accountID]);
    accountSelected = accountList[accountID]
    isAccountSelected = true;
    updateAccountSelectionStatus();
    $.ajax({
      url: "https://localhost:3000/accountSelection?selectedAccount=" + accountSelected,
    });
  }
}

/** Documented by Sean
 *@constuctor
 * Updating the selected account for taskpane UI display */
function updateAccountSelectionStatus()
{
  $('#account-selection').toggle(false);
  $('#signature-content').toggle(true);
  buildSignatureList('#signatures-list', signatureList, accountSelected);
}

function getAccountIndices(accountName)
{
  var indices = []
  i = 0;

  signatureList.forEach(function(sig) {
    console.log(sig.substr(0, accountName.length))
    if (sig.substr(0, accountName.length) == accountName){
      indices.push(i);
    }
    i++;
  });
  return indices;
}

function getAccount()
  {
    return accountSelected;
  }

function getIsAccountSelected()
{
  return isAccountSelected;
}

function logOut()
{
  console.log("logging out " + accountSelected);
  $.ajax({
    url: "https://localhost:3000/logout?loggedOut=" + accountSelected
  });
  isAccountSelected = false;
  accountSelected = "";
  $('#account-selection').toggle(true);
  $('#signature-content').toggle(false);

}
