---<Sean Kruse>---

-- Define a constant representing infinity
local INF = math.huge

-- Function to read an adjacency matrix from a file
local function adjMatFromFile(filename)
    local file = io.open(filename, "r")
    local n_verts = tonumber(file:read("*l"))
    print(" n_verts = " .. n_verts)
    local adjmat = {}
    for i = 1, n_verts do
        adjmat[i] = {}
        for j = 1, n_verts do
            adjmat[i][j] = INF
        end
        adjmat[i][i] = 0
    end
    for line in file:lines() do
        local int_list = {}
        for num in line:gmatch("%d+") do
            table.insert(int_list, tonumber(num))
        end
        -- I had to adjust the indices by 1 to match the Python version
        local vert = table.remove(int_list, 1) + 1
        assert(#int_list % 2 == 0)
        local n_neighbors = #int_list // 2
        local neighbors = {}
        local distances = {}
        for n = 1, #int_list, 2 do
        -- I had to adjust the indices by 1 to match the Python version
            table.insert(neighbors, int_list[n] + 1)
            table.insert(distances, int_list[n + 1])
        end
        for i = 1, n_neighbors do
            adjmat[vert][neighbors[i]] = distances[i]
        end
    end
    file:close()
    return adjmat
end

--Function to print an adjacency matrix
local function printAdjMat(mat, width)
    width = width or 3
    local header_row = {}
    for i = 1, #mat do
        table.insert(header_row, string.format("%" .. width .. "d", i - 1))
    end
    local res_str = '     ' .. table.concat(header_row, ' ') .. '\n'
    res_str = res_str .. '    ' .. string.rep('-', (width + 1) * #mat) .. '\n'
    for i, row in ipairs(mat) do
        local row_str = {}
        for _, elem in ipairs(row) do
            table.insert(row_str, elem < INF and string.format("%" .. width .. "d", elem) or ' 999')
        end
        res_str = res_str .. string.format(" %2d |", i - 1) .. table.concat(row_str, " ") .. "\n"
    end
    print(res_str)
end

--Floyd's algorithm
local function floyd(W)
    local distance = {}
    for i = 1, #W do
        distance[i] = {}
        for j = 1, #W do
            distance[i][j] = W[i][j]
        end
    end
    for k = 1, #W do
        for i = 1, #W do
            for j = 1, #W do
                if distance[i][j] > distance[i][k] + distance[k][j] then
                    distance[i][j] = distance[i][k] + distance[k][j]
                end
            end
        end
    end
    return distance
end

local function assign03_main()
-- Read the adjacency matrix from the input file
local g = adjMatFromFile("py_vs_X_assign3.txt")
-- Measure the elapsed time taken by Floyd's algorithm
local start_time = os.clock()
local res_floyd = floyd(g)
local elapsed_time_floyd = os.clock() - start_time
-- Print the timing results
print(string.format("  Floyd's elapsed time: %.2f", elapsed_time_floyd))
-- Print the adjacency matrix
printAdjMat(res_floyd, 3)
end


assign03_main()
