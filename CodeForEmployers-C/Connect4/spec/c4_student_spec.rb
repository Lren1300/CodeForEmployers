#
# Logan Reneau and Allison Fitzgerald 
#
require 'spec_helper'

describe 'C4 standard board size' do
    #start horizontal tests
    it 'detects P1 winning horizontally in row 0' do
        # Notice that the game should end before the last 'q' input is consumed
        # This will be apart of every test 
        #passed
        result = test_c4('aabbccdq')
        expect(result).to declare_win_for 1
    end

    it 'detects P1 winning horizontally in row 1' do
        #passed
        result = test_c4('abcdaabbccdq')
        expect(result).to declare_win_for 1
    end

    it 'detects P1 winning horizontally in row 2' do
        #Starts with layering then does the original test code
        #pass
        result = test_c4('abcddcbaaabbccdq')
        expect(result).to declare_win_for 1
    end

    it 'detects P1 winning horizontally in row 3' do
        #Starts with layering then does the original test code
        #pass
        result = test_c4('abcddcbaabcdaabbccdq')
        expect(result).to declare_win_for 1
    end
    #fixed this
    it 'detects P1 winning horizontally in row 4' do
        #Starts with layering then does the original test code
        #pass
        result = test_c4('abcddcbadcbaabcdaabbccdq')
        expect(result).to declare_win_for 1
    end
    #fixed this
    it 'detects P1 winning horizontally in row 5' do
        #Starts with layering then does the original test code
        #pass
        result = test_c4('aaaaabbbbbccccdceddddfaebecedq')
        expect(result).to declare_win_for 1
    end
    
    #start vertical tests 

    it 'detects P1 winning vertically in column 0' do
        #pass
        result = test_c4('abababaq')
        expect(result).to declare_win_for 1
    end

    it 'detects P1 winning vertically in column 1' do
        #pass
        result = test_c4('bcbcbcbq')
        expect(result).to declare_win_for 1
    end

    it 'detects P1 winning vertically in column 2' do
        #pass
        result = test_c4('cdcdcdcq')
        expect(result).to declare_win_for 1
    end

    it 'detects P1 winning vertically in column 3' do
        #pass
        result = test_c4('dedededq')
        expect(result).to declare_win_for 1
    end

    it 'detects P1 winning vertically in column 4' do
        #pass
        result = test_c4('efefefeq')
        expect(result).to declare_win_for 1
    end

    it 'detects P1 winning vertically in column 5' do
        #pass
        result = test_c4('fgfgfgfq')
        expect(result).to declare_win_for 1
    end

    it 'detects P1 winning vertically in column 6' do
        #pass
        result = test_c4('gagagagq')
        expect(result).to declare_win_for 1
    end


    # start diagonal tests, start first starting row

    it 'detects P1 winning diagonally for case 1' do
        #pass
        result = test_c4('abbccacddddq')
        expect(result).to declare_win_for 1
    end

    it 'detects P1 winning diagonally  for case 2' do
        #pass
        result = test_c4('bccddadeeeeq')
        expect(result).to declare_win_for 1
    end

    it 'detects P1 winning diagonally  for case 3' do
        #pass
        result = test_c4('cddeeaeffffq')
        expect(result).to declare_win_for 1
    end

    it 'detects P1 winning diagonally  for case 4' do
        #pass
        result = test_c4('deeffafggggq')
        expect(result).to declare_win_for 1
    end

    it 'detects P1 winning diagonally  for case 5' do
        #pass
        result = test_c4('gffeegeddddq')
        expect(result).to declare_win_for 1
    end

    it 'detects P1 winning diagonally  for case 6' do
        #pass
        result = test_c4('feeddgdccccq')
        expect(result).to declare_win_for 1
    end

    it 'detects P1 winning diagonally for case 7' do
        #pass
        result = test_c4('eddccgcbbbbq')
        expect(result).to declare_win_for 1
    end

    it 'detects P1 winning diagonally for case 8' do
        #pass
        result = test_c4('dccbbgbaaaaq')
        expect(result).to declare_win_for 1
    end

    #End first row, start second starting row

    #abcdefg for odd 
    #badcfeg for even 1-4
    #fgdebca for even 5-8
    #these are to avoid false wins in our tests

    it 'detects P1 winning diagonally for case 9' do
        #pass
        result = test_c4('ababbccccddddgdq')
        expect(result).to declare_win_for 1
    end

    it 'detects P1 winning diagonally  for case 10' do
        #pass
        result = test_c4('bcbccddddeeeeaeq')
        expect(result).to declare_win_for 1
    end
    it 'detects P1 winning diagonally  for case 11' do
        #pass
        result = test_c4('cdcddeeeeffffbfq')
        expect(result).to declare_win_for 1
    end
    it 'detects P1 winning diagonally  for case 12' do
        #pass
        result = test_c4('dedeeffffggggcgq')
        expect(result).to declare_win_for 1
    end
 
    it 'detects P1 winning diagonally  for case 13' do
        #pass
        result = test_c4('fggffeeeeddddcdq')
        expect(result).to declare_win_for 1
    end

    it 'detects P1 winning diagonally  for case 14' do
        #pass
        result = test_c4('effeeddddccccbcq')
        expect(result).to declare_win_for 1
    end

    it 'detects P1 winning diagonally for case 15' do
        #pass
        result = test_c4('deeddccccbbbbabq')
        expect(result).to declare_win_for 1
    end

    it 'detects P1 winning diagonally for case 16' do
        #pass
        result = test_c4('cddccbbbbaaaagaq')
        expect(result).to declare_win_for 1
    end

    #end row 2, start third and final starting row

    #baacdefggfedcb checkered bottom two rows for odd starting index
    #abcdefgabcdefg checkered bottom two rows for even starting index
    #these are to avoid winning the game from the rows below our test
    
    it 'detects P1 winning diagonally for case 17' do
        #pass
        result = test_c4('baacdefggfedcbabbccbcddddq')
        expect(result).to declare_win_for 1
    end

    it 'detects P1 winning diagonally  for case 18' do
        #pass
        result = test_c4('abcdefgabcdefgbccddcdeeeeq')
        expect(result).to declare_win_for 1
    end

    it 'detects P1 winning diagonally  for case 19' do
        #pass
        result = test_c4('baacdefggfedcbcddeedeffffq')
        expect(result).to declare_win_for 1
    end

    it 'detects P1 winning diagonally  for case 20' do
        #pass
        result = test_c4('abcdefgabcdefgdeeffefggggq')
        expect(result).to declare_win_for 1
    end

    it 'detects P1 winning diagonally  for case 21' do
        #pass
        result = test_c4('baacdefggfedcbgffeefeddddq')
        expect(result).to declare_win_for 1
    end

    it 'detects P1 winning diagonally  for case 22' do
        #pass
        result = test_c4('abcdefgabcdefgfeeddedccccq')
        expect(result).to declare_win_for 1
    end

    it 'detects P1 winning diagonally for case 23' do
        #pass
        result = test_c4('baacdefggfedcbeddccdcbbbbq')
        expect(result).to declare_win_for 1
    end

    it 'detects P1 winning diagonally for case 24' do
        #pass
        result = test_c4('abcdefgabcdefgdccbbcbaaaaq')
        expect(result).to declare_win_for 1
    end

    #End player 1 winning tests
    #------------------------------------------------------------------------------
    #Start player 2 winning tests

    #start hortizontal tests

    it 'detects P2 winning horizontally in row 0' do
        # Notice that the game should end before the last 'q' input is consumed
        # This will be apart of every test 
        #pass
        result = test_c4('abacadgeq')
        expect(result).to declare_win_for 2
    end

    it 'detects P2 winning horizontally in row 1' do
        #Starts with layering then does the original test code
        #pass
        result = test_c4('aabbccgdgdq')
        expect(result).to declare_win_for 2
    end

    it 'detects P2 winning horizontally in row 2' do
        #Starts with layering then does the original test code
        #pass
        result = test_c4('aabbccgddgfafbfcedq')
        expect(result).to declare_win_for 2
    end
        #fixed
    it 'detects P2 winning horizontally in row 3' do
        #Starts with layering then does the original test code
        #pass
        result = test_c4('aaabbbcccgdddagbgcgdq')
        expect(result).to declare_win_for 2
    end

    it 'detects P2 winning horizontally in row 4' do
        #Starts with layering then does the original test code
        #pass
        result = test_c4('aaabbbcccdddaabbccgdgdq')
        expect(result).to declare_win_for 2
    end
    #fixed
    it 'detects P2 winning horizontally in row 5' do
        #Starts with layering then does the original test code
        #pass
        result = test_c4('aaaaagbbbbbcccccdddddagbgcgdq')
        expect(result).to declare_win_for 2
    end

    #start vertical tests

    it 'detects P2 winning vertically in column 0' do
        #pass
        result = test_c4('bababagaq')
        expect(result).to declare_win_for 2
    end

    it 'detects P2 winning vertically in column 1' do
        #pass
        result = test_c4('abababgbq')
        expect(result).to declare_win_for 2
    end

    it 'detects P2 winning vertically in column 2' do
        #pass
        result = test_c4('dcdcdcgcq')
        expect(result).to declare_win_for 2
    end

    it 'detects P2 winning vertically in column 3' do
        #pass
        result = test_c4('edededgdq')
        expect(result).to declare_win_for 2
    end

    it 'detects P2 winning vertically in column 4' do
        #pass
        result = test_c4('fefefegeq')
        expect(result).to declare_win_for 2
    end

    it 'detects P2 winning vertically in column 5' do
        #pass
        result = test_c4('gfgfgfafq')
        expect(result).to declare_win_for 2
    end

    it 'detects P2 winning vertically in column 6' do
        #pass
        result = test_c4('agagagbgq')
        expect(result).to declare_win_for 2
    end

    # start diagonal tests, start first starting row

    it 'detects P2 winning diagonally for case 1' do
        #pass
        result = test_c4('bacbccddddq')
        expect(result).to declare_win_for 2
    end

    it 'detects P2 winning diagonally  for case 2' do
        #pass
        result = test_c4('cbdcddeeeeq')
        expect(result).to declare_win_for 2
    end

    it 'detects P2 winning diagonally  for case 3' do
        #pass
        result = test_c4('dcedeeffffq')
        expect(result).to declare_win_for 2
    end

    it 'detects P2 winning diagonally  for case 4' do
        #pass
        result = test_c4('edfeffggggq')
        expect(result).to declare_win_for 2
    end

    it 'detects P2 winning diagonally  for case 5' do
        #pass
        result = test_c4('fgefeeddddq')
        expect(result).to declare_win_for 2
    end

    it 'detects P2 winning diagonally  for case 6' do
        #pass
        result = test_c4('efdeddccccq')
        expect(result).to declare_win_for 2
    end

    it 'detects P2 winning diagonally for case 7' do
        #pass
        result = test_c4('decdccbbbbq')
        expect(result).to declare_win_for 2
    end

    it 'detects P2 winning diagonally for case 8' do
        #pass
        result = test_c4('cdbcbbaaaaq')
        expect(result).to declare_win_for 2
    end

    #End first row, start second starting row

    #abcdefg for even
    #badcfeg for odd 1-4
    #fgdebca for odd 5-8
    #these are to avoid false wins in our tests
    

    it 'detects P2 winning diagonally for case 9' do
        #pass
        result = test_c4('aabbcbcdccddddq')
        expect(result).to declare_win_for 2
    end

    it 'detects P2 winning diagonally  for case 10' do
        #pass
        result = test_c4('bbccdcdeddeeeeq')
        expect(result).to declare_win_for 2
    end
 
    it 'detects P2 winning diagonally  for case 11' do
        #pass
        result = test_c4('ccddedefeeffffq')
        expect(result).to declare_win_for 2
    end

    it 'detects P2 winning diagonally  for case 12' do
        #pass
        result = test_c4('ddeefefgffggggq')
        expect(result).to declare_win_for 2
    end

    it 'detects P2 winning diagonally  for case 13' do
        #pass
        result = test_c4('cedgfgffedeeddcdq')
        expect(result).to declare_win_for 2
    end

    it 'detects P2 winning diagonally  for case 14' do
        #pass
        result = test_c4('bdcfefeedcddccbcq')
        expect(result).to declare_win_for 2
    end

    it 'detects P2 winning diagonally for case 15' do
        #pass
        result = test_c4('acbededdcbccbbabq')
        expect(result).to declare_win_for 2
    end

    it 'detects P2 winning diagonally for case 16' do
        #pass
        result = test_c4('gbadcdccbabbaagaq')
        expect(result).to declare_win_for 2
    end
    
    #end row 2, start third and final starting row

    #baacdefggfedcb checkered bottom two rows for even starting index
    #abcdefgabcdefg checkered bottom two rows for odd starting index
    #these are to avoid winning the game from the rows below our test
     
    it 'detects P2 winning diagonally for case 17' do
        #pass
        result = test_c4('abcdefgabcdefgbacbccddddq')
        expect(result).to declare_win_for 2
    end

    it 'detects P2 winning diagonally  for case 18' do
        #pass
        result = test_c4('baacdefggfedcbcbdcddeeeeq')
        expect(result).to declare_win_for 2
    end

    it 'detects P2 winning diagonally  for case 19' do
        #pass
        result = test_c4('abcdefgabcdefgdcedeeffffq')
        expect(result).to declare_win_for 2
    end

    it 'detects P2 winning diagonally  for case 20' do
        #pass
        result = test_c4('baacdefggfedcbedfeffggggq')
        expect(result).to declare_win_for 2
    end

    it 'detects P2 winning diagonally  for case 21' do
        #pass
        result = test_c4('abcdefgabcdefgfgefeeddddq')
        expect(result).to declare_win_for 2
    end

    it 'detects P2 winning diagonally  for case 22' do
        #pass
        result = test_c4('baacdefggfedcbefdeddccccq')
        expect(result).to declare_win_for 2
    end

    it 'detects P2 winning diagonally for case 23' do
        #pass
        result = test_c4('abcdefgabcdefgdecdccbbbbq')
        expect(result).to declare_win_for 2
    end

    it 'detects P2 winning diagonally for case 24' do
        #pass
        result = test_c4('baacdefggfedcbcdbcbbaaaaq')
        expect(result).to declare_win_for 2
    end
    
    #End player 2 winning tests

    #tie????
    #it 'game ends in a tie' do
   #    result = test_c4('abcdefgabcdefgbadcfegbadcfegabcdefgabcdefg')
    #    expect(result).to ???
    #end

    it 'quits before declaring a winner in column 0' do
        result = test_c4('abababq')
        expect(result).to be_abandoned
    end

end

describe 'Connect 4 multple board size' do
    #checks there are 3 columns 
    it 'detects P1 winning on last 2 columns' do
        result = test_c4('BBDCq', 3, 3, 2)
        expect(result).to declare_win_for 1
    end 

    #checks there are 3 no more than 3 rows 
    it 'detects P1 winning on first 2 columns' do
        #pass
        result = test_c4('AAACABq', 3, 3, 2,)
        expect(result).to declare_win_for 1
    end

    #checks there are at least 3 rows
    it 'detects P1 winning through filling the first column' do
        #pass
        result = test_c4('ABABAq', 3, 3, 3)
        expect(result).to declare_win_for 1
    end

    it 'detects P1 winning by 3 in a row horizontally' do
        #pass
        result = test_c4('aabbcq', 3, 3, 3)
        expect(result).to declare_win_for 1
    end

    it 'detects P1 winning by 3 in a row diagonally' do
        #pass
        result = test_c4('abbccacq', 3, 3, 3)
        expect(result).to declare_win_for 1
    end

    #checks there are 4 columns 
    it 'detects P1 winning on last 2 columns' do
        result = test_c4('CCEDq', 4, 4, 2)
        expect(result).to declare_win_for 1
    end
    #checks there are no more than 4 rows 
    it 'detects P1 winning on first 2 columns' do
        result = test_c4('AAAAABq', 4, 4, 2,)
        expect(result).to declare_win_for 1
    end
    #checks there are at least 4 rows
    it 'detects P1 winning through filling the first column' do
        result = test_c4('ABABABAq', 4, 4, 4)
        expect(result).to declare_win_for 1
    end

    #checks there are 5 columns 
    it 'detects P1 winning on last 2 columns' do
        result = test_c4('DDFEq', 5, 5, 2)
        expect(result).to declare_win_for 1
    end
    #checks there are no more than 5 rows 
    it 'detects P1 winning on first 2 columns' do
        result = test_c4('AAAAAACABq', 5, 5, 2,)
        expect(result).to declare_win_for 1
    end
    #checks there are at least 5 rows
    it 'detects P1 winning through filling the first column' do
        result = test_c4('ABABABABAq', 5, 5, 5)
        expect(result).to declare_win_for 1
    end

    it 'detects P1 winning by 5 in a row horizontally' do
        result = test_c4('aabbccddeq', 5, 5, 5)
        expect(result).to declare_win_for 1
    end

    it 'detects P1 winning by 5 in a row diagonally' do
        result = test_c4('abbccacddddeeeeaeq', 5, 5, 5)
        expect(result).to declare_win_for 1
    end
    #checks there are 6 columns 
    it 'detects P1 winning on last 2 columns' do
        result = test_c4('EEGFq', 6, 6, 2)
        expect(result).to declare_win_for 1
    end
    #checks there are no more than 6 rows 
    it 'detects P1 winning on first 2 columns' do
        result = test_c4('AAAAAAABq', 6, 6, 2,)
        expect(result).to declare_win_for 1
    end
    #checks there are at least 6 rows
    it 'detects P1 winning through filling the first column' do
        result = test_c4('ABABABABABAq', 6, 6, 6)
        expect(result).to declare_win_for 1
    end

    it 'detects P1 winning by 6 in a row horizontally' do
        result = test_c4('aabbccddeefq', 6, 6, 6)
        expect(result).to declare_win_for 1
    end

    it 'detects P1 winning by 6 in a row diagonally' do
        result = test_c4('abbccacddddeeeeaeffffffq', 6, 6, 6)
        expect(result).to declare_win_for 1
    end

    #checks there are 7 columns 
    it 'detects P1 winning on last 2 columns' do
        result = test_c4('FFHGq', 7, 7, 2)
        expect(result).to declare_win_for 1
    end
    #checks there are no more than 7 rows 
    it 'detects P1 winning on first 2 columns' do
        result = test_c4('AAAAAAACABq', 7, 7, 2,)
        expect(result).to declare_win_for 1
    end
    #checks there are at least 7 rows
    it 'detects P1 winning through filling the first column' do
        result = test_c4('ABABABABABABAq', 7, 7, 7)
        expect(result).to declare_win_for 1
    end

    it 'detects P1 winning by 7 in a row horizontally' do
        result = test_c4('aabbccddeeffgq', 7, 7, 7)
        expect(result).to declare_win_for 1
    end

    it 'detects P1 winning by 7 in a row diagonally' do
        result = test_c4('abbccacddddeeeeaeffffffggggggagq', 7, 7, 7)
        expect(result).to declare_win_for 1
    end

    #checks there are 8 columns 
    it 'detects P1 winning on last 2 columns' do
        result = test_c4('GGIHq', 8, 8, 2)
        expect(result).to declare_win_for 1
    end
    #checks there are no more than 8 rows 
    it 'detects P1 winning on first 2 columns' do
        result = test_c4('AAAAAAAAABq', 8, 8, 2)
        expect(result).to declare_win_for 1
    end
    #checks there are at least 8 rows
    it 'detects P1 winning through filling the first column' do
        result = test_c4('ABABABABABABABAq', 8, 8, 8)
        expect(result).to declare_win_for 1
    end

    it 'detects P1 winning by 8 in a row horizontally' do
        result = test_c4('aabbccddeeffgghq', 8, 8, 8)
        expect(result).to declare_win_for 1
    end

    it 'detects P1 winning by 8 in a row diagonally' do
        result = test_c4('abbccacddddeeeeaeffffffggggggaghhhhhhhhq', 8, 8, 8)
        expect(result).to declare_win_for 1
    end

    #checks there are 9 columns 
    it 'detects P1 winning on last 2 columns' do
        result = test_c4('HHJIq', 9, 9, 2)
        expect(result).to declare_win_for 1
    end
    #checks there are no more than 9 rows 
    it 'detects P1 winning on first 2 columns' do
        result = test_c4('AAAAAAAAACABq', 9, 9, 2)
        expect(result).to declare_win_for 1
    end
    #checks there are at least 9 rows
    it 'detects P1 winning through filling the first column' do
        result = test_c4('ABABABABABABABABAq', 9, 9, 9)
        expect(result).to declare_win_for 1
    end
    
    it 'detects P1 winning by 9 in a row horizontally' do
        result = test_c4('aabbccddeeffgghhiq', 9, 9, 9)
        expect(result).to declare_win_for 1
    end

    it 'detects P1 winning by 9 in a row diagonally' do
        result = test_c4('abbccacddddeeeeaeffffffggggggaghhhhhhhhiiiiiiiiaiq', 9, 9, 9)
        expect(result).to declare_win_for 1
    end

    #checks there are 10 columns 
    it 'detects P1 winning on last 2 columns' do
        result = test_c4('IIKJq', 10, 10, 2)
        expect(result).to declare_win_for 1
    end
    #checks there are no more than 10 rows 
    it 'detects P1 winning on first 2 columns' do
        result = test_c4('AAAAAAAAAAABq', 10, 10, 2)
        expect(result).to declare_win_for 1
    end
    #checks there are at least 10 rows
    it 'detects P1 winning through filling the first column' do
        result = test_c4('ABABABABABABABABABAq', 10, 10, 10)
        expect(result).to declare_win_for 1
    end
        
    it 'detects P1 winning by 10 in a row horizontally' do
        result = test_c4('aabbccddeeffgghhiijq', 10, 10, 10)
        expect(result).to declare_win_for 1
    end

    it 'detects P1 winning by 10 in a row diagonally' do
        result = test_c4('abbccacddddeeeeaeffffffggggggaghhhhhhhhiiiiiiiiaijjjjjjjjjjq', 10, 10, 10)
        expect(result).to declare_win_for 1
    end

    #checks there are 11 columns 
    it 'detects P1 winning on last 2 columns' do
        result = test_c4('JAKJ', 11, 11, 2)
        expect(result).to declare_win_for 1
    end
    #checks there are no more than 11 rows 
    it 'detects P1 winning on first 2 columns' do
        result = test_c4('AAAAAAAAAAACABq', 11, 11, 2)
        expect(result).to declare_win_for 1
    end
    #checks there are at least 11 rows
    it 'detects P1 winning through filling the first column' do
        result = test_c4('ABABABABABABABABABABAq', 11, 11, 11)
        expect(result).to declare_win_for 1
    end
        
    it 'detects P1 winning by 11 in a row horizontally' do
        result = test_c4('aabbccddeeffgghhiijjkq', 11, 11, 11)
        expect(result).to declare_win_for 1
    end

    it 'detects P1 winning by 11 in a row diagonally' do
        result = test_c4('abbccacddddeeeeaeffffffggggggaghhhhhhhhiiiiiiiiaijjjjjjjjjjkkkkkkkkkkakq', 11, 11, 11)
        expect(result).to declare_win_for 1
    end

    #checks there are 12 columns 
    it 'detects P1 winning on last 2 columns' do
        result = test_c4('KALq', 12, 12, 2)
        expect(result).to declare_win_for 1
    end
    #checks there are no more than 12 rows 
    it 'detects P1 winning on first 2 columns' do
        result = test_c4('AAAAAAAAAAAAABq', 12, 12, 2)
        expect(result).to declare_win_for 1
    end
    #checks there are at least 12 rows
    it 'detects P1 winning through filling the first column' do
        result = test_c4('ABABABABABABABABABABABAq', 12, 12, 12)
        expect(result).to declare_win_for 1
    end

    it 'detects P1 winning by 12 in a row horizontally' do
        result = test_c4('aabbccddeeffgghhiijjkklq', 12, 12, 12)
        expect(result).to declare_win_for 1
    end

    it 'detects P1 winning by 12 in a row diagonally' do
        result = test_c4('abbccacddddeeeeaeffffffggggggaghhhhhhhhiiiiiiiiaijjjjjjjjjjkkkkkkkkkkakllllllllllllq', 12, 12, 12)
        expect(result).to declare_win_for 1
    end

    #checks there are 13 columns 
    it 'detects P1 winning on last 2 columns' do
        result = test_c4('LAMq', 13, 13, 2)
        expect(result).to declare_win_for 1
    end
    #checks there are no more than 13 rows 
    it 'detects P1 winning on first 2 columns' do
        result = test_c4('AAAAAAAAAAAAACABq', 13, 13, 2)
        expect(result).to declare_win_for 1
    end
    #checks there are at least 13 rows
    it 'detects P1 winning through filling the first column' do
        result = test_c4('ABABABABABABABABABABABABAq', 13, 13, 13)
        expect(result).to declare_win_for 1
    end

    it 'detects P1 winning by 13 in a row horizontally' do
        result = test_c4('aabbccddeeffgghhiijjkkllmq', 13, 13, 13)
        expect(result).to declare_win_for 1
    end

    it 'detects P1 winning by 13 in a row diagonally' do
        result = test_c4('abbccacddddeeeeaeffffffggggggaghhhhhhhhiiiiiiiiaijjjjjjjjjjkkkkkkkkkkakllllllllllllmmmmmmmmmmmmamq', 13, 13, 13)
        expect(result).to declare_win_for 1
    end
    
    #checks there are 14 columns 
    it 'detects P1 winning on last 2 columns' do
        result = test_c4('MANq', 14, 14, 2)
        expect(result).to declare_win_for 1
    end
    #checks there are no more than 14 rows 
    it 'detects P1 winning on first 2 columns' do
        result = test_c4('{AAAAAAAAAAAAAAABq', 15, 15, 2)
        expect(result).to declare_win_for 1
    end
    #checks there are at least 14 rows
    it 'detects P1 winning through filling the first column' do
        result = test_c4('ABABABABABABABABABABABABABABAq', 15, 15, 15)
        expect(result).to declare_win_for 1
    end

    it 'detects P1 winning by 14 in a row horizontally' do
        result = test_c4('aabbccddeeffgghhiijjkkllmmnq', 14, 14, 14)
        expect(result).to declare_win_for 1
    end

    it 'detects P1 winning by 14 in a row diagonally' do
        result = test_c4('abbccacddddeeeeaeffffffggggggaghhhhhhhhiiiiiiiiaijjjjjjjjjjkkkkkkkkkkakllllllllllllmmmmmmmmmmmmamnnnnnnnnnnnnnnq', 14, 14, 14)
        expect(result).to declare_win_for 1
    end
    

    #checks there are 15 columns 
    it 'detects P1 winning on last 2 columns' do
        result = test_c4('NAOq', 15, 15, 2)
        expect(result).to declare_win_for 1
    end
    #checks there are no more than 15 rows 
    it 'detects P1 winning on first 2 columns' do
        result = test_c4('AAAAAAAAAAAAAAACABq', 15, 15, 2)
        expect(result).to declare_win_for 1
    end
    #checks there are at least 15 rows
    it 'detects P1 winning through filling the first column' do
        result = test_c4('ABABABABABABABABABABABABABABAq', 15, 15, 15)
        expect(result).to declare_win_for 1
    end

    it 'detects P1 winning by 15 in a row horizontally' do
        result = test_c4('aabbccddeeffgghhiijjkkllmmnnoq', 15, 15, 15)
        expect(result).to declare_win_for 1
    end

    it 'detects P1 winning by 15 in a row diagonally' do
        result = test_c4('abbccacddddeeeeaeffffffggggggaghhhhhhhhiiiiiiiiaijjjjjjjjjjkkkkkkkkkkakllllllllllllmmmmmmmmmmmmamnnnnnnnnnnnnnnooooooooooooooaoq', 15, 15, 15)
        expect(result).to declare_win_for 1
    end

    #checks there are 16 columns 
    it 'detects P1 winning on last 2 columns' do
        result = test_c4('OOAGPq', 16, 16, 2)
        expect(result).to declare_win_for 1
    end
    #checks there are no more than 16 rows 
    it 'detects P1 winning on first 2 columns' do
        result = test_c4('AAAAAAAAAAAAAAAAABq', 16, 16, 2)
        expect(result).to declare_win_for 1
    end
    #checks there are at least 16 rows
    it 'detects P1 winning through filling the first column' do
        result = test_c4('ABABABABABABABABABABABABABABABAq', 16, 16, 16)
        expect(result).to declare_win_for 1
    end
    
    it 'detects P1 winning by 16 in a row horizontally' do
        result = test_c4('aabbccddeeffgghhiijjkkllmmnnoopq', 16, 16, 16)
        expect(result).to declare_win_for 1
    end

    it 'detects P1 winning by 16 in a row diagonally' do
        result = test_c4('abbccacddddeeeeaeffffffggggggaghhhhhhhhiiiiiiiiaijjjjjjjjjjkkkkkkkkkkakllllllllllllmmmmmmmmmmmmamnnnnnnnnnnnnnnooooooooooooooaoppppppppppppppppq', 16, 16, 16)
        expect(result).to declare_win_for 1
    end

end

describe 'Connect 4 alternate' do
    it 'detects player 2 winning horizontally on a big board' do
        result = test_c4('iaabbccddeeffgq', 3, 9, 7)
        expect(result).to declare_win_for 2
    end

    it 'quits before declaring a winner in column 0' do
        result = test_c4('iaabbccddeeffq', 3, 9, 7)
        expect(result).to be_abandoned
    end
end



