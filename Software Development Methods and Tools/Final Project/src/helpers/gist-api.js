/** Documented by Sean
 *@constuctor
 *@param {string} user - user is a string that is correlated to the id tag for the html div that will be populated with the signatures
 *@param {String} callback - callback function as param
 *@callback
 */
function getUserGists(user, callback) {
    var requestUrl = 'https://api.github.com/users/' + user + '/gists';
  
    $.ajax({
      url: requestUrl,
      dataType: 'json'
    }).done(function(gists){
      callback(gists);
    }).fail(function(error){
      callback(null, error);
    });
  }

  /** Documented by Sean
 *@constuctor
 *@param {string} parent - parent is a string that is correlated to the id tag for the html div that will be populated with the signatures
 *@param {String} gists - pulls gists from gist function
 *@param {String} clickFunc - 
 *@callback
 */
  function buildGistList(parent, gists, clickFunc) {
    gists.forEach(function(gist) {
  
      var listItem = $('<div/>')
        .appendTo(parent);
  
      var radioItem = $('<input>')
        .addClass('ms-ListItem')
        .addClass('is-selectable')
        .attr('type', 'radio')
        .attr('name', 'gists')
        .attr('tabindex', 0)
        .val(gist.id)
        .appendTo(listItem);
  
      var desc = $('<span/>')
        .addClass('ms-ListItem-primaryText')
        .text(gist.description)
        .appendTo(listItem);
  
      var desc = $('<span/>')
        .addClass('ms-ListItem-secondaryText')
        .text(' - ' + buildFileList(gist.files))
        .appendTo(listItem);
        
      var updated = new Date(gist.updated_at);
  
      var desc = $('<span/>')
        .addClass('ms-ListItem-tertiaryText')
        .text(' - Last updated ' + updated.toLocaleString())
        .appendTo(listItem);
  
      listItem.on('click', clickFunc);
    });  
  }

  /** Documented by Sean
 *@constuctor
 *@param {string} files - files to build a list from text file of signatures
 */
  function buildFileList(files) {
  
    var fileList = '';
  
    for (var file in files) {
      if (files.hasOwnProperty(file)) {
        if (fileList.length > 0) {
          fileList = fileList + ', ';
        }
  
        fileList = fileList + files[file].filename + ' (' + files[file].language + ')';
      }
    }
  
    return fileList;
  }

  /** Documented by Sean
  *@constuctor
  *@param {string} gistId - grabbing specific ID from url GitHub gists
  *@param {String} callback - callback function as param
  *@callback
  */
  function getGist(gistId, callback) {
    var requestUrl = 'https://api.github.com/gists/' + gistId;
  
    $.ajax({
      url: requestUrl,
      dataType: 'json'
    }).done(function(gist){
      callback(gist);
    }).fail(function(error){
      callback(null, error);
    });
  }
  
  /** Documented by Sean & Phillip
  *@constuctor
  *@param {string} gist - grabbing file in the gist
  *@param {String} callback - callback function as param
  *@callback
  */
  function buildBodyContent(gist, callback) {
    /** Find the first non-truncated file in the gist
    and use it. */
    for (var filename in gist.files) {
      if (gist.files.hasOwnProperty(filename)) {
        var file = gist.files[filename];
        if (!file.truncated) {
          /** We have a winner. */
          switch (file.language) {
            case 'HTML':
              /** Insert as-is. */
              callback(file.content);
              break;
            case 'Markdown':
              /** Convert Markdown to HTML. */
              var converter = new showdown.Converter();
              var html = converter.makeHtml(file.content);
              callback(html);
              break;
            default:
              /** Insert contents as a <code> block. */
              var codeBlock = '<pre><code>';
              codeBlock = codeBlock + file.content;
              codeBlock = codeBlock + '</code></pre>';
              callback(codeBlock);
          }
          return;
        }
      }
    }
    callback(null, 'No suitable file found in the gist');
  }
