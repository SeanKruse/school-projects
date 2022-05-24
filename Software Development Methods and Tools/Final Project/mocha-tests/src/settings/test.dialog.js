
const expect = require('chai').expect;
const multiplyNumbers = require('../../../src/settings/dialog.js').multiplyNumbers;

describe('Dialog', function () {
    describe('#multiplyNumbers()', function () {
        it('should return the result of multiplication', function () {
            let result = multiplyNumbers(4, 6);
            expect(result).to.equal(24);
        });
    });
});