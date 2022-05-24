global.Office = () => ({});
global.Office.onReady = () => ({});

var chai = require('chai');
var assert = chai.assert; // using Assert Style
var expect = chai.expect; //Using Expect Style
var should = chai.should; // Using Should Style

//Skeleton Example from Team Two's Repo for inspiration for future tests
/*describe('apply Signature test', () => {
    it('title should not be null',()=>{
    const title = window.document.getElementById('signature');
    expect(title).toBeDefined();
    });
    it('email Message Should equal singature with title',()=>{
        const _title = 'hello';
        const singatureList= [
        {title: 'hello', message: 'Message'}
        ]
        let _message = null;
        singatureList.forEach(({title, message})=>{
        if (_title === title)_message = message;
        });
        expect(_message).toEqual('Message');
        });
        it ('message should be null',()=>{});
        });
        
var add = require ('./taskpane');

describe('Apply Random Signature from the list', () => {
    test('Should be signature message apply random mailbox', () => {
        const result = add.signatureList[add.getRandom()-1]
        expect(result.length).toBeGreaterThanOrEqual*(0) ;

    });
});*/

//Code by Sean - Generic test that empty array should have length of 0
describe('Array', function() {
  it('should start empty', function() {
    var arr = [];

    assert.equal(arr.length, 0);
  });

  //Code adapted by Sean - Generic Test that compares arrays have same members - Taken from https://stackoverflow.com/questions/44335770/node-mocha-chai-unit-tests-compare-array-of-objects-regardless-of-order
   it('should have the same members', function() {
   var genArrA = [
   {name: 'Sean', age: 35},
   {name: 'Weston', age: 30},
   ]

   //assume we got the result in this order
   var genArrB = [
   {name: 'Sean', age: 35},
   {name: 'Weston', age: 30},
   ]

   expect(genArrA).to.have.deep.members(genArrB)
  });
  
  //Code written by Sean - Generic Test that var array should have expected properties
  /**it('should have property name with value Sean', function(){
      var a = [
          {name: 'Sean', age: 35},    
          ]
      
      expect(a).to.have.property('name');
  });
  
  //Code written by Sean - Generic Test that var array should have expected properties
  it('should have property age with value 35', function(){
      var b = [
          {name: 'Sean', age: 35},    
          ]
      
      expect(b).to.have.property('age');
  });  
  */
    
  //Code by Sean and Weston - Generic test that created array has the correct number of members
  it('should contain the correct number of members', function() {
    var accountList = ["Philip", "Sean", "Weston"];

    assert.equal(accountList.length, 3);
  });
});
