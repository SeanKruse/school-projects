const expect = require('chai').expect;
const assert = require('chai').assert;
const multiplyNumbers = require('../../../src/commands/commands.js').multiplyNumbers;
const randomSignature = require('../../../src/commands/commands.js').randomSignature;
//const className = require('../../../src/commands/test.className.js');
//var addClass = className.addClass;

//Code Adapted by Sean for Generic Math Tests - Taken from https://stackabuse.com/testing-node-js-code-with-mocha-and-chai/
describe('Math', function() {
    describe('#abs()', function() {
        it('should return positive value of given negative number', function() {
            expect(Math.abs(-5)).to.be.equal(5);
        });

        it('should return positive value of given positive number', function() {
            expect(Math.abs(3)).to.be.equal(3);
        });

        it('should return 0 given 0', function() {
            expect(Math.abs(0)).to.be.equal(0);
        });
    });
    
//Code Adapted by Sean for Generic Math Tests - Taken from https://stackabuse.com/testing-node-js-code-with-mocha-and-chai/    
    describe('#sqrt()', function() {
        it('should return the square root of a given positive number', function() {
            expect(Math.sqrt(25)).to.be.equal(5);
        });

        it('should return NaN for a given negative number', function() {
            expect(Math.sqrt(-9)).to.be.NaN;
        });

        it('should return 0 given 0', function() {
            expect(Math.sqrt(0)).to.be.equal(0);
        });
    });
    
//Code by Phillip - Reformatted by Sean - Expected multiplication test
describe('#multiplyNumbers()', function () {
    it('should return the result of multiplication', function () {
        let result = multiplyNumbers(4, 6);
            expect(result).to.equal(24);
        });
    });
});


//Added by Sean - Code taken from https://www.sitepoint.com/unit-test-javascript-mocha-chai/
function addClass(el, newClass) {
  if(el.className.indexOf(newClass) !== -1) {
    return;
  }

  if(el.className !== '') {
    //ensure class names are separated by a space
    newClass = ' ' + newClass;
  }

  el.className += newClass;
}

//Added by Sean - Code taken from https://www.sitepoint.com/unit-test-javascript-mocha-chai/
describe('addClass', function() {
  it('should add class into element', function() {
    var element = { className: '' };

    addClass(element, 'test-class');

    assert.equal(element.className, 'test-class');
  });  
  
  it('should not add a class which already exists in element', function() {
    var element = { className: 'exists' };

    addClass(element, 'exists');

    var numClasses = element.className.split(' ').length;
    assert.equal(numClasses, 1);
  });
  
  it('should append new class after existing one', function() {
    var element = { className: 'exists' };

    addClass(element, 'new-class');

    var classes = element.className.split(' ');
    assert.equal(classes[1], 'new-class');
  });
});


