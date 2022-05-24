global.Office = () => ({});

//var fs= require('fs');

var config;
var btnEvent;

//var output = document.getElementById("output");

//  var raw Files = FileReader;
//}

/** var LinkedList = newFunction()
    The initialize function must be run each time a new page is loaded. */
Office.initialize = function (reason) {   
};

/** Documented by Sean
* @deprecated
Code by weston
prints the list items
printList()
{
    var curr = this.head;
    var str = "";
    while (curr) {
        str += curr.element + " ";
        curr = curr.next;
    }
    console.log(str);
}
 random function for LinkedList implementation
function random(result) {
  var LinkedList = require('linked-list')

  var Signature1 = newLinkedList.item('Weston J. Lake')
  var Signature2 = newLinkedList.item('Weston Lake')
  var Signature3 = newLinkedList.item('weston j. lake')
  var Signature4 = newLinkedList.item('WJL')
  var Signature5 = newLinkedList.item('WL')
  var list = newLinkedList(Signature1, Signature2, Signature3, Signature4, Signature5)
  var randomNum = math.random(list.size)
  if (randomNum != 0) {
    while (randomNum >= 0) {
      list.head.next
      randomNum--
      console.log(list.Signature1);
      list.printList();
    }
    return list.head;
  }
  else {
    console.log(Signature1);
    list.printList();
    return list.head;
  }
}

 addEventHandler method
object.addEventHandler("click", random()); */

/** Documented by Phillip & Sean
 *  @constructor
 *  @param {String} error - error message
 * Add any UI-less function here. */
function showError(error) {
  Office.context.mailbox.item.notificationMessages.replaceAsync('github-error', {
    type: 'errorMessage',
    message: error
  }, function(result){
  });
}

var settingsDialog;

function insertDefaultGist(event) {

  config = getConfig();

  /** Check if the add-in has been configured. */
  if (config && config.defaultGistId) {
    /** Get the default gist content and insert. */
    try {
      getGist(config.defaultGistId, function(gist, error) {
        if (gist) {
          buildBodyContent(gist, function (content, error) {
            if (content) {
              Office.context.mailbox.item.body.setSelectedDataAsync(content,
                {coercionType: Office.CoercionType.Html}, function(result) {
                  event.completed();
                });               

            } else {
              showError(error);
              event.completed();
            }
          });
        } else {
          showError(error);
          event.completed();
        }
      });
    } catch (err) {
      showError(err);
      event.completed();
    }

  } else {
    /** Save the event object so we can finish up later. */
    btnEvent = event;
    /** Not configured yet, display settings dialog with
        warn=1 to display warning. */
    var url = new URI('../src/settings/dialog.html?warn=1').absoluteTo(window.location).toString();
    var dialogOptions = { width: 20, height: 40, displayInIframe: true };

    Office.context.ui.displayDialogAsync(url, dialogOptions, function(result) {
      settingsDialog = result.value;
      settingsDialog.addEventHandler(Office.EventType.DialogMessageReceived, receiveMessage);
      settingsDialog.addEventHandler(Office.EventType.DialogEventReceived, dialogClosed);
    });
  }
}

function receiveMessage(message) {
  config = JSON.parse(message.message);
  setConfig(config, function(result) {
    settingsDialog.close();
    settingsDialog = null;
    btnEvent.completed();
    btnEvent = null;
  });
}

function dialogClosed(message) {
  settingsDialog = null;
  btnEvent.completed();
  btnEvent = null;
}

function getGlobal() {
  return (typeof self !== "undefined") ? self :
    (typeof window !== "undefined") ? window :
    (typeof global !== "undefined") ? global :
    undefined;
}

var g = getGlobal();

/** The add-in command functions need to be available in global scope. */
g.insertDefaultGist = insertDefaultGist;



function multiplyNumbers(x, y) {
  return x * y;
}

/**
*@deprecated
Deprecated - Code by Sean & Weston
var sig = fs.readFileSync("assets/signatures.txt").toString().split("\n");
for (i in sig) {
  console.log(sig[i])
}
*@deprecated
Deprecated - Code by Sean & Weston
function randomSignature() {
  var sig = fs.readFileSync('assets/signatures.txt').toString().split("\n");
  var randomNumber = Math.floor(Math.random() * (sig.length));
  return sig[randomNumber];
}
*/

module.exports = {
  multiplyNumbers : multiplyNumbers
  //randomSignature: randomSignature
}
