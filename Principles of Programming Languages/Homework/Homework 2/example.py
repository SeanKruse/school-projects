'''
 * CS3210 - Principles of Programming Languages - Fall 2022
 * Instructor: Thyago Mota
 * Student: 
 * Description: Homework 02 - Diamond Problem
'''

class Device: 

    def __init__(self, manufacturer):
        self.manufacturer = manufacturer

    def __str__(self):
        return f'({self.manufacturer})'

class Printer(Device): 

    def __init__(self, manufacturer, type):
        self.manufacturer = manufacturer
        self.type = type

    def __str__(self):
        return f'({self.manufacturer}, {type})'

class Scanner(Device): 

    def __init__(self, manufacturer, dpi):
        self.manufacturer = manufacturer
        self.dpi = dpi
         

    def __str__(self):
        return f'Scanner({self.manufacturer}, {self.dpi})'

class MultifunctionPrinter(Printer, Scanner): 

    def __init__(self, manufacturer, type, dpi):
        self.manufacturer = manufacturer
        self.type = type
        self.dpi = dpi

    def __str__(self):
        return f'({self.manufacturer}, {self.type}, {self.dpi})'

if __name__ == '__main__':
    ts6300 = MultifunctionPrinter("Canon", "Inkjet", "600")
    print(ts6300)