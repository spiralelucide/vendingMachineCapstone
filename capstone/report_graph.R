library("ggplot2")
sales_report <- read.csv("/Users/bryant/workspaces/vendingMachineCapstone/capstone/SalesReport.csv")
ggplot(sales_report, aes(x = Item, y = number_sold)) + geom_bar(stat = 'identity') + coord_flip()
