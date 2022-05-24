<<<<<<< HEAD
/** Documented by Sean
 *@constuctor
 */
function getConfig() {
    var config = {};
  
    config.gitHubUserName = Office.context.roamingSettings.get('gitHubUserName');
    config.defaultGistId = Office.context.roamingSettings.get('defaultGistId');
  
    return config;
  }

 /** Documented by Sean
 *@constuctor
 *@param {string} config - office configuration paramater
 *@param {string} callback - callback function as paramater
 *@callback
 */
  function setConfig(config, callback) {
    Office.context.roamingSettings.set('gitHubUserName', config.gitHubUserName);
    Office.context.roamingSettings.set('defaultGistId', config.defaultGistId);
  
    Office.context.roamingSettings.saveAsync(callback);
  }
=======
/** Documented by Sean
 *@constuctor
 */
function getConfig() {
    var config = {};
  
    config.gitHubUserName = Office.context.roamingSettings.get('gitHubUserName');
    config.defaultGistId = Office.context.roamingSettings.get('defaultGistId');
  
    return config;
  }

 /** Documented by Sean
 *@constuctor
 *@param {string} config - office configuration paramater
 *@param {string} callback - callback function as paramater
 *@callback
 */
  function setConfig(config, callback) {
    Office.context.roamingSettings.set('gitHubUserName', config.gitHubUserName);
    Office.context.roamingSettings.set('defaultGistId', config.defaultGistId);
  
    Office.context.roamingSettings.saveAsync(callback);
  }
>>>>>>> 2887b22c357d6bcfa54a1422e88da01327c9ff60
