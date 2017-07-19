#store current directory
initial.dir <- getwd()
#change to new directory
setwd("/Users/bryant/workspaces/vendingMachineCapstone/capstone")
#load libraries
library("ggplot2")
#load the dataset
sales_report <- read.csv("SalesReport.csv")
pdf(file = 'sales_plot.pdf',width=6,height=4)
ggplot(sales_report, aes(x = Item, y = number_sold)) + geom_bar(stat = 'identity') + coord_flip()
dev.off()
setwd(initial.dir)
